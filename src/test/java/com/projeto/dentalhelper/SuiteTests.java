package com.projeto.dentalhelper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.projeto.dentalhelper.repositories.CategoriaDespesaRepositoryTest;

import org.junit.runners.Suite;

@RunWith(Suite.class)
@SuiteClasses({
	CategoriaDespesaRepositoryTest.class,
})
public class SuiteTests {

}
