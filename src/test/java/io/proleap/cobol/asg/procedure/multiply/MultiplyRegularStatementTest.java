package io.proleap.cobol.asg.procedure.multiply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Regular;
import io.proleap.cobol.asg.metamodel.procedure.multiply.RegularOperand;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MultiplyRegularStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/multiply/MultiplyRegularStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MultiplyRegularStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final MultiplyStatement multiplyStatement = (MultiplyStatement) procedureDivision.getStatements().get(0);
			assertNotNull(multiplyStatement);
			assertEquals(StatementTypeEnum.MULTIPLY, multiplyStatement.getStatementType());
			assertEquals(MultiplyStatement.Type.REGULAR, multiplyStatement.getType());

			{
				final Call operandCall = multiplyStatement.getOperandCall();
				assertNotNull(operandCall);
				assertEquals(Call.CallType.UNDEFINED_CALL, operandCall.getCallType());
			}

			{
				final Regular regular = multiplyStatement.getRegular();
				assertNotNull(regular);
				assertEquals(2, regular.getOperands().size());

				{
					final RegularOperand operand = regular.getOperands().get(0);
					assertTrue(operand.isRounded());

					final Call operandCall = operand.getOperandCall();
					assertNotNull(operandCall);
					assertEquals(Call.CallType.UNDEFINED_CALL, operandCall.getCallType());
				}

				{
					final RegularOperand operand = regular.getOperands().get(1);
					assertFalse(operand.isRounded());

					final Call operandCall = operand.getOperandCall();
					assertNotNull(operandCall);
					assertEquals(Call.CallType.UNDEFINED_CALL, operandCall.getCallType());
				}
			}

			{
				final OnSizeError onSizeError = multiplyStatement.getOnSizeError();
				assertNotNull(onSizeError);
				assertEquals(1, onSizeError.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) onSizeError.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotOnSizeError notOnSizeError = multiplyStatement.getNotOnSizeError();
				assertNotNull(notOnSizeError);
				assertEquals(1, notOnSizeError.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notOnSizeError.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}