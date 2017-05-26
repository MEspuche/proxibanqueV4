package com.huios.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de tests de la couche DAO
 * 
 * @author Perrine Stephane Vincent Marine
 */
@RunWith(Suite.class)
@SuiteClasses({ TestCRUDClient.class, TestCRUDCompte.class, TestCRUDConseiller.class })
public class AllTests {

}
