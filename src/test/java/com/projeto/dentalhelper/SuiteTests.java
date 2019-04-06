package com.projeto.dentalhelper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.projeto.dentalhelper.repositories.CategoriaDespesaRepositoryTest;
import com.projeto.dentalhelper.repositories.DespesaRepositoryTest;
import com.projeto.dentalhelper.repositories.MaterialRepositoryTest;
import com.projeto.dentalhelper.repositories.PacienteRepositoryTest;
import com.projeto.dentalhelper.repositories.ProcedimentoRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({
	CategoriaDespesaRepositoryTest.class,
	DespesaRepositoryTest.class,
	ProcedimentoRepositoryTest.class,
	MaterialRepositoryTest.class,
	PacienteRepositoryTest.class
	
})
public class SuiteTests {

}
