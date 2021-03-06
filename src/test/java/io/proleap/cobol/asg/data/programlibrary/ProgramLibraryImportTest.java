package io.proleap.cobol.asg.data.programlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.programlibrary.CommonClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportAttribute;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportAttribute.Type;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntryImport;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProgramLibraryImportTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/programlibrary/ProgramLibraryImport.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ProgramLibraryImport");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProgramLibrarySection programLibrarySection = dataDivision.getProgramLibrarySection();

		{
			final LibraryDescriptionEntry libraryDescriptionEntry = programLibrarySection
					.getLibraryDescriptionEntry("SOMELIB");
			assertNotNull(libraryDescriptionEntry);
			assertEquals(LibraryDescriptionEntry.Type.IMPORT, libraryDescriptionEntry.getType());

			final LibraryDescriptionEntryImport libraryDescriptionEntryImport = (LibraryDescriptionEntryImport) libraryDescriptionEntry;

			{
				final GlobalClause globalClause = libraryDescriptionEntryImport.getGlobalClause();
				assertNotNull(globalClause);
				assertTrue(globalClause.isGlobal());
			}

			{
				final CommonClause commonClause = libraryDescriptionEntryImport.getCommonClause();
				assertNotNull(commonClause);
				assertTrue(commonClause.isCommon());
			}

			{
				final List<ImportAttribute> importAttributes = libraryDescriptionEntryImport.getImportAttributes();
				assertEquals(1, importAttributes.size());

				final ImportAttribute importAttribute = importAttributes.get(0);
				assertEquals(Type.BY_TITLE, importAttribute.getType());
				assertEquals("123", importAttribute.getFunctionLiteral().getValue());
				assertEquals("234", importAttribute.getParameterLiteral().getValue());
				assertEquals("'SOMETITLE'", importAttribute.getTitleLiteral().getValue());
			}

			{
				final List<ImportEntryProcedure> importEntryProcedures = libraryDescriptionEntryImport
						.getImportEntryProcedures();
				assertEquals(1, importEntryProcedures.size());

				final ImportEntryProcedure importEntryProcedure = importEntryProcedures.get(0);
				assertEquals("123", importEntryProcedure.getForClause().getForLiteral().getValue());

				assertNotNull(importEntryProcedure.getUsingClause());
				assertEquals(2, importEntryProcedure.getUsingClause().getUsingValueStmts().size());

				assertNotNull(importEntryProcedure.getWithClause());
				assertEquals(2, importEntryProcedure.getWithClause().getWithValueStmts().size());

				assertNotNull(importEntryProcedure.getGivingClause().getGivingCall());
			}
		}
	}
}