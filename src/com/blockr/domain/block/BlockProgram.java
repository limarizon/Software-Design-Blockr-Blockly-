package com.blockr.domain.block;

import com.blockr.domain.block.interfaces.*;
import com.blockr.domain.gameworld.GameWorld;
import com.blockr.ui.components.programblocks.ProgramBlockInsertInfo;

import java.util.*;
import java.util.stream.Collectors;

public class BlockProgram implements ReadOnlyBlockProgram {

    public BlockProgram(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    private final GameWorld gameWorld;

    /**
     Returns the first block for every blockchain that forms a program.
     */
    @Override
    public List<? extends ReadOnlyStatementBlock> getComponents() {
        return Collections.unmodifiableList(components);
    }

    public int getBlockCount(){
        return blocks.size();
    }

    public ReadOnlyStatementBlock getActive() {

        if(currentBlock != null)
            return currentBlock.getActive();

        if(canStart())
            return components.get(0).getActive();

        return null;
    }

    //First block of every chain program
    private final List<StatementBlock> components = new LinkedList<>();
    //All blocks in the programArea
    private final Set<Block> blocks = new HashSet<>();
    //Blocks connected to each other
    private final List<StatementBlock> connectedBlocks = new LinkedList<>();

    private StatementBlock currentBlock;

    /**
     * Returns whether or not the program is in a valid state and can be started
     */
    public boolean canStart(){

        if(components.size() != 1)
            return false;

        return getCompositeBlocks().stream().allMatch(CompositeBlock::isReady);
    }

    public void executeNext(){

        if(!canStart())
            throw new RuntimeException("The BlockProgram did not contain any blocks.");

        if(currentBlock == null){
            currentBlock = components.get(0);
        }

        currentBlock = currentBlock.execute(gameWorld);
    }

    public void clear(){
        blocks.clear();
        components.clear();
    }

    public void reset(){
        getCompositeBlocks().forEach(CompositeBlock::reset);
        currentBlock = null;
    }

    private Set<CompositeBlock> getCompositeBlocks(){
        return blocks.stream().filter(b -> b instanceof CompositeBlock).map(b -> (CompositeBlock)b).collect(Collectors.toSet());
    }

    /**
     * Add a new block to the program, not connected to any other blocks
     */
    public void addBlock(ReadOnlyStatementBlock statementBlock){

        ensureValidStatementBlock(statementBlock, "statementBlock");

        //noinspection SuspiciousMethodCalls
        if(blocks.contains(statementBlock)){
            throw new IllegalArgumentException("The given statementBlock already exists");
        }

        components.add((StatementBlock) statementBlock);
        blocks.add(statementBlock);
    }

    /**
     * Connects the plug of plugBlock to the socket of socketBlock
     */
    public void connectToStatementSocket(ReadOnlyStatementBlock socketBlock, ReadOnlyStatementBlock plugBlock){

        ensureValidStatementBlock(socketBlock, "socketBlock");
        ensureValidStatementBlock(plugBlock, "plugBlock");

        if(!blocks.contains(socketBlock)){
            addBlock(socketBlock);
        }

        var rwSocketBlock = (StatementBlock)socketBlock;
        var rwPlugBlock = (StatementBlock)plugBlock;

        rwSocketBlock.setNext(rwPlugBlock);

        if(rwPlugBlock.getPrevious() != null){

            rwPlugBlock.getPrevious().setNext(null);
        }

        rwPlugBlock.setPrevious(rwSocketBlock);

        if(blocks.contains(plugBlock))
            return;

        blocks.add(rwPlugBlock);
    }

    /**
     * Disconnects the plug of plugBlock from the socket of socketBlock
     */
    public void disconnectStatementBlock(ReadOnlyStatementBlock socketBlock, ReadOnlyStatementBlock plugBlock){

        ensureValidStatementBlock(socketBlock, "socketBlock");
        ensureValidStatementBlock(plugBlock, "plugBlock");

        if(!blocks.contains(socketBlock)){
            throw new IllegalArgumentException("The given socketBlock does not exist");
        }

        if(!blocks.contains(plugBlock)){
            throw new IllegalArgumentException("The given plugBlock does not exist");
        }

        var rwSocketBlock = (StatementBlock)socketBlock;
        var rwPlugBlock = (StatementBlock)plugBlock;

        rwSocketBlock.setNext(null);

        components.add(rwPlugBlock);
    }

    private static void ensureValidStatementBlock(ReadOnlyStatementBlock roBlock, String argName){

        if(roBlock == null){
            throw new IllegalArgumentException(String.format("The given %s must be effective", argName));
        }

        if(!(roBlock instanceof StatementBlock))
            throw new IllegalArgumentException(String.format("The given %s must be an instance of StatementBlock", argName));
    }

    //TODO: too general. Need to make more specific validators.
    private static void ensureValidStatementBlock(Block block, String argName){

        if(block == null){
            throw new IllegalArgumentException(String.format("The given %s must be effective", argName));
        }

        if(!(block instanceof Block))
            throw new IllegalArgumentException(String.format("The given %s must be an instance of Block", argName));
    }


    public ReadOnlyStatementBlock getRootBlock(Block blockOfChain){
        ensureValidStatementBlock(blockOfChain, "block");
        if(blockOfChain instanceof ConditionBlock){
            return getRootBlock(((ConditionBlock) blockOfChain).getParent());
        }
        if(blockOfChain instanceof StatementBlock){
            var newRoot = ((StatementBlock) blockOfChain).getPrevious();
            if(newRoot==null)
                return (ReadOnlyStatementBlock) blockOfChain;
            return getRootBlock(newRoot);
        }
        return null;
    }

    /*
    Function that inserts a given plug block into a socket block, at least 1 of these 2 blocks already exists in the blocks
    set (don't know which). PlugLocation returns the location where the socket should be plugged if it can't be inferred.
    @return Should return the root component of the program that was modified so the visual graph can be rebuild
    PlugLocation: Body / Other
     */
    public void insertIntoBody(Block plug,Block socket){
        //TO DO wat als plug een previous heeft
        if(!blocks.contains(socket))
            return;
        if(!(socket instanceof ControlFlowBlock))
            return;
        var rwSocketBlock = (ControlFlowBlock) socket;
        if(!(plug instanceof StatementBlock))
            return;
        var rwPlugBlock = (StatementBlock) plug;
        if(rwSocketBlock.getBody()!= null){
            var previous = rwSocketBlock.getBody();
            rwSocketBlock.setBody(rwPlugBlock);
            rwPlugBlock.setPrevious(rwSocketBlock);
            rwPlugBlock.setNext(previous);
            previous.setPrevious(rwPlugBlock);
        }
        else{
            rwSocketBlock.setBody(rwPlugBlock);
            rwPlugBlock.setPrevious(rwSocketBlock);
        }
        if(!blocks.contains(plug)){
            blocks.add(plug);
        }
    }

    public void plugStatementOnStatement(Block plug, Block socket){
        if(!(plug instanceof StatementBlock))
            return;
        var rwPlugBlock = (StatementBlock)plug;
        if(!(socket instanceof StatementBlock))
            return;
        var rwSocketBlock = (StatementBlock)socket;
        if(rwPlugBlock.getPrevious()!=null){
            var previous = rwPlugBlock.getPrevious();
            rwPlugBlock.setPrevious(rwSocketBlock);
            rwSocketBlock.setNext(rwPlugBlock);
            rwSocketBlock.setPrevious(previous);
            previous.setNext(rwSocketBlock);
        }
        else if(rwSocketBlock.getNext()!=null){
            var next = rwSocketBlock.getNext();
            rwSocketBlock.setNext(rwPlugBlock);
            rwPlugBlock.setPrevious(rwSocketBlock);
            rwPlugBlock.setNext(next);
            next.setPrevious(rwPlugBlock);
        }
        else{
            rwSocketBlock.setNext(rwPlugBlock);
            rwPlugBlock.setPrevious(rwSocketBlock);
        }
        if(!blocks.contains(plug))
            blocks.add(plug);
        if(!blocks.contains(socket))
            blocks.add(socket);
        if(components.contains(plug)){
            components.remove(plug);
            components.add((StatementBlock) socket);}
    }
    public void plugConditionOntoControlFlow(Block plug, Block socket){
        if(!(plug instanceof ConditionBlock))
            return;
        var rwPlugBlock = (ConditionBlock)plug;
        if(!(socket instanceof ControlFlowBlock))
            return;
        var rwSocketBlock = (ControlFlowBlock)socket;
        if(rwSocketBlock.getCondition()!=null && rwPlugBlock instanceof NotBlock){
            var prev = rwSocketBlock.getCondition();
            rwSocketBlock.setCondition(rwPlugBlock);
            rwPlugBlock.setParent(rwSocketBlock);
            rwPlugBlock.setParent((ConditionBlock) null);
            ((NotBlock) rwPlugBlock).setCondition(prev);
            prev.setParent(rwPlugBlock);
        }
        else if(rwSocketBlock.getCondition()!=null && !(rwPlugBlock instanceof NotBlock)){
            return;
        }
        else {
            rwSocketBlock.setCondition(rwPlugBlock);
            rwPlugBlock.setParent(rwSocketBlock);
        }
        if(!blocks.contains(plug))
            blocks.add(plug);
    };
    public void plugConditionOntoCondition(Block plug, Block socket){
        if(!(plug instanceof ConditionBlock))
            return;
        var rwPlugBlock = (ConditionBlock)plug;
        if(!(socket instanceof ConditionBlock))
            return;
        var rwSocketBlock = (ConditionBlock)socket;
        if(rwPlugBlock instanceof NotBlock && rwSocketBlock instanceof NotBlock &&  ((NotBlock) rwSocketBlock).getCondition()!=null ){
         var prev = ((NotBlock) rwSocketBlock).getCondition();
         ((NotBlock) rwSocketBlock).setCondition(rwPlugBlock);
         rwPlugBlock.setParent(rwSocketBlock.getParent());
         rwPlugBlock.setParent(rwSocketBlock);
         prev.setParent(rwPlugBlock);
        }
        else if(rwSocketBlock instanceof NotBlock && rwPlugBlock instanceof NotBlock && rwPlugBlock.getConditionParent()!=null){
           var prev = rwPlugBlock.getConditionParent();
           rwSocketBlock.setParent(prev);
           rwSocketBlock.setParent(prev.getParent());
           ((NotBlock) rwSocketBlock).setCondition(rwPlugBlock);
           rwPlugBlock.setParent(rwSocketBlock);
           ((NotBlock) prev).setCondition(rwSocketBlock);
        }
        else if(rwSocketBlock instanceof NotBlock && rwPlugBlock instanceof NotBlock){
            ((NotBlock) rwSocketBlock).setCondition(rwPlugBlock);
            rwPlugBlock.setParent(rwSocketBlock.getParent());
            rwPlugBlock.setParent(rwSocketBlock);
        }
        if(!blocks.contains(plug))
            blocks.add(plug);
        if(!blocks.contains(socket))
            blocks.add(socket);
    };

    public Block processInsertBlock(ProgramBlockInsertInfo programBlockInsertInfo){
        Block plug = programBlockInsertInfo.getPlug();
        Block socket = programBlockInsertInfo.getSocket();
        var location = programBlockInsertInfo.getPlugLocation();

        // TODO: update to more statementvalidation ensurers, this is too general.
        ensureValidStatementBlock(socket, "socketBlock");
        ensureValidStatementBlock(plug, "plugBlock");

        // for statement into body of CFB
        if(location == ProgramBlockInsertInfo.PlugLocation.BODY){
            insertIntoBody(plug,socket);
        }
        // for every other possibility
        else if (location == ProgramBlockInsertInfo.PlugLocation.OTHER){
            if(!(plug instanceof ConditionBlock))
            plugStatementOnStatement(plug,socket);
            else if(socket instanceof ControlFlowBlock ) {
                plugConditionOntoControlFlow(plug,socket);
            }
            else if(socket instanceof ConditionBlock) {
                plugConditionOntoCondition(plug, socket);
            }
            else{
                throw new RuntimeException("Not a correct block.");
            }
        }
        else{
            throw new IllegalArgumentException("Location has to be type of ProgramBlockInsertInfo.BODY or ProgramBlockInsertInfo.OTHER.");
        }
        if(blocks.contains(socket) && blocks.contains(plug)){
            //if(socket instanceof ConditionBlock)
              //  return getRootBlock(((ConditionBlock) socket).getParent());
            return getRootBlock(socket);
        }
        else{
            throw new RuntimeException("Either plug or socket were not valid parts of the blocks.");
        }
    }
}
