package io.proleap.cobol.asg.environment.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.CollatingSequenceClause;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.DiskSizeClause;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.MemorySizeClause;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.SegmentLimitClause;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ConfigurationSectionTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/configuration/ConfigurationSection.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ConfigurationSection");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final ConfigurationSection configurationSection = environmentDivision.getConfigurationSection();
		final SourceComputerParagraph sourceComputerParagraph = configurationSection.getSourceComputerParagraph();
		final ObjectComputerParagraph objectComputerParagraph = configurationSection.getObjectComputerParagraph();

		assertTrue(sourceComputerParagraph.isDebuggingMode());
		assertEquals("XYZ", sourceComputerParagraph.getName());

		final MemorySizeClause memorySizeClause = objectComputerParagraph.getMemorySizeClause();
		final IntegerLiteralValueStmt memorySizeValueStmt = (IntegerLiteralValueStmt) memorySizeClause.getValueStmt();
		assertEquals(new Integer(8192), memorySizeValueStmt.getValue());
		assertEquals(MemorySizeClause.Unit.CHARACTERS, memorySizeClause.getUnit());

		final DiskSizeClause diskSizeClause = objectComputerParagraph.getDiskSizeClause();
		final IntegerLiteralValueStmt diskSizeValueStmt = (IntegerLiteralValueStmt) diskSizeClause.getValueStmt();
		assertEquals(new Integer(4096), diskSizeValueStmt.getValue());
		assertEquals(DiskSizeClause.Unit.WORDS, diskSizeClause.getUnit());

		final CollatingSequenceClause collatingSequenceClause = objectComputerParagraph.getCollatingSequenceClause();
		final List<String> alphabetNames = collatingSequenceClause.getAlphabetNames();
		assertEquals(2, alphabetNames.size());
		assertEquals("Special-Sequence1", alphabetNames.get(0));
		assertEquals("Special-Sequence2", alphabetNames.get(1));
		assertEquals("Special-Sequence-Alpha", collatingSequenceClause.getAlphaNumeric());
		assertEquals("Special-Sequence-National", collatingSequenceClause.getNational());

		final SegmentLimitClause segmentLimitClause = objectComputerParagraph.getSegmentLimitClause();
		final IntegerLiteral segmentLimitIntegerLiteral = segmentLimitClause.getIntegerLiteral();
		assertEquals(new Integer(128), segmentLimitIntegerLiteral.getValue());
	}
}
