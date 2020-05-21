package com.blocker.gameworld.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameGridTest.class,
     RobotGameWorldTest.class,
     ApiUtilitiesTest.class,
        RobotOrientationAndPositionTest.class,
        GameWorldTypeTest.class
})

public class GameWorldImplDomainTest {
}
