package io.proleap.cobol.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AcceptStatementContext;
import io.proleap.cobol.Cobol85Parser.AddStatementContext;
import io.proleap.cobol.Cobol85Parser.AlterProceedToContext;
import io.proleap.cobol.Cobol85Parser.AlterStatementContext;
import io.proleap.cobol.Cobol85Parser.AtEndPhraseContext;
import io.proleap.cobol.Cobol85Parser.CallByContentStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByValueStatementContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.Cobol85Parser.CancelCallContext;
import io.proleap.cobol.Cobol85Parser.CancelStatementContext;
import io.proleap.cobol.Cobol85Parser.CdNameContext;
import io.proleap.cobol.Cobol85Parser.CloseFileContext;
import io.proleap.cobol.Cobol85Parser.CloseStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStoreContext;
import io.proleap.cobol.Cobol85Parser.ContinueStatementContext;
import io.proleap.cobol.Cobol85Parser.DeleteStatementContext;
import io.proleap.cobol.Cobol85Parser.DisableStatementContext;
import io.proleap.cobol.Cobol85Parser.DisplayOperandContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideStatementContext;
import io.proleap.cobol.Cobol85Parser.EnableStatementContext;
import io.proleap.cobol.Cobol85Parser.EntryStatementContext;
import io.proleap.cobol.Cobol85Parser.EvaluateAlsoSelectContext;
import io.proleap.cobol.Cobol85Parser.EvaluateStatementContext;
import io.proleap.cobol.Cobol85Parser.EvaluateWhenPhraseContext;
import io.proleap.cobol.Cobol85Parser.ExhibitOperandContext;
import io.proleap.cobol.Cobol85Parser.ExhibitStatementContext;
import io.proleap.cobol.Cobol85Parser.ExitStatementContext;
import io.proleap.cobol.Cobol85Parser.GenerateStatementContext;
import io.proleap.cobol.Cobol85Parser.GoToStatementContext;
import io.proleap.cobol.Cobol85Parser.GobackStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IfStatementContext;
import io.proleap.cobol.Cobol85Parser.InitializeStatementContext;
import io.proleap.cobol.Cobol85Parser.InitiateStatementContext;
import io.proleap.cobol.Cobol85Parser.InspectStatementContext;
import io.proleap.cobol.Cobol85Parser.InvalidKeyPhraseContext;
import io.proleap.cobol.Cobol85Parser.MergeGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.MergeOnKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.MergeStatementContext;
import io.proleap.cobol.Cobol85Parser.MergeUsingContext;
import io.proleap.cobol.Cobol85Parser.MoveStatementContext;
import io.proleap.cobol.Cobol85Parser.MultiplyStatementContext;
import io.proleap.cobol.Cobol85Parser.NotAtEndPhraseContext;
import io.proleap.cobol.Cobol85Parser.NotInvalidKeyPhraseContext;
import io.proleap.cobol.Cobol85Parser.NotOnExceptionClauseContext;
import io.proleap.cobol.Cobol85Parser.NotOnOverflowPhraseContext;
import io.proleap.cobol.Cobol85Parser.NotOnSizeErrorPhraseContext;
import io.proleap.cobol.Cobol85Parser.OnExceptionClauseContext;
import io.proleap.cobol.Cobol85Parser.OnOverflowPhraseContext;
import io.proleap.cobol.Cobol85Parser.OnSizeErrorPhraseContext;
import io.proleap.cobol.Cobol85Parser.OpenExtendStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenIOStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenInputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.PurgeStatementContext;
import io.proleap.cobol.Cobol85Parser.ReadStatementContext;
import io.proleap.cobol.Cobol85Parser.ReceiveStatementContext;
import io.proleap.cobol.Cobol85Parser.ReleaseStatementContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.ReturnStatementContext;
import io.proleap.cobol.Cobol85Parser.RewriteStatementContext;
import io.proleap.cobol.Cobol85Parser.SearchStatementContext;
import io.proleap.cobol.Cobol85Parser.SearchWhenContext;
import io.proleap.cobol.Cobol85Parser.SendStatementContext;
import io.proleap.cobol.Cobol85Parser.SetStatementContext;
import io.proleap.cobol.Cobol85Parser.SetToStatementContext;
import io.proleap.cobol.Cobol85Parser.SortGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortOnKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SortStatementContext;
import io.proleap.cobol.Cobol85Parser.SortUsingContext;
import io.proleap.cobol.Cobol85Parser.StartStatementContext;
import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.Cobol85Parser.StringSendingPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractStatementContext;
import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
import io.proleap.cobol.Cobol85Parser.UnstringStatementContext;
import io.proleap.cobol.Cobol85Parser.WriteStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEnd;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEnd;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotOnException;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnException;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement.Type;
import io.proleap.cobol.asg.metamodel.procedure.accept.impl.AcceptStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.impl.AddStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.alter.impl.AlterStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.impl.CallStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.asg.metamodel.procedure.cancel.impl.CancelStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.impl.CloseStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.asg.metamodel.procedure.compute.impl.ComputeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.asg.metamodel.procedure.continuestmt.impl.ContinueStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.asg.metamodel.procedure.delete.impl.DeleteStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.asg.metamodel.procedure.disable.impl.DisableStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.impl.DisplayStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.impl.DivideStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.asg.metamodel.procedure.enable.impl.EnableStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.asg.metamodel.procedure.entry.impl.EntryStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.impl.EvaluateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.ExhibitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.impl.ExhibitStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exit.impl.ExitStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.asg.metamodel.procedure.generate.impl.GenerateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.asg.metamodel.procedure.goback.impl.GobackStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.impl.GoToStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.impl.IfStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.AtEndImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.InvalidKeyImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotAtEndImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotInvalidKeyImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotOnExceptionImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotOnOverflowImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotOnSizeErrorImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.OnExceptionImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.OnOverflowImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.OnSizeErrorImpl;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.impl.InitializeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.asg.metamodel.procedure.initiate.impl.InitiateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.impl.InspectStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.asg.metamodel.procedure.merge.impl.MergeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.impl.MoveStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.impl.MultiplyStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.impl.OpenStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.impl.PerformStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.purge.PurgeStatement;
import io.proleap.cobol.asg.metamodel.procedure.purge.impl.PurgeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.impl.ReadStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.impl.ReceiveStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.release.ReleaseStatement;
import io.proleap.cobol.asg.metamodel.procedure.release.impl.ReleaseStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.ReturnStatement;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.impl.ReturnStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.RewriteStatement;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.impl.RewriteStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.impl.SearchStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.asg.metamodel.procedure.send.impl.SendStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.SetStatement;
import io.proleap.cobol.asg.metamodel.procedure.set.impl.SetStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.asg.metamodel.procedure.sort.impl.SortStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.start.StartStatement;
import io.proleap.cobol.asg.metamodel.procedure.start.impl.StartStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.impl.StopStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.impl.StringStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.impl.SubtractStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.terminate.TerminateStatement;
import io.proleap.cobol.asg.metamodel.procedure.terminate.impl.TerminateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.impl.UnstringStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.asg.metamodel.procedure.write.impl.WriteStatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;

public class ScopeImpl extends CobolDivisionElementImpl implements Scope {

	private final static Logger LOG = LogManager.getLogger(ScopeImpl.class);

	protected List<Statement> statements = new ArrayList<Statement>();

	public ScopeImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
	}

	@Override
	public AcceptStatement addAcceptStatement(final AcceptStatementContext ctx) {
		AcceptStatement result = (AcceptStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptStatementImpl(programUnit, this, ctx);

			// accept call
			final Call acceptCall = createCall(ctx.identifier());
			result.setAcceptCall(acceptCall);

			// type
			final Type type;

			if (ctx.acceptFromDateStatement() != null) {
				result.addAcceptFromDate(ctx.acceptFromDateStatement());
				type = Type.DATE;
			} else if (ctx.acceptFromMnemonicStatement() != null) {
				result.addAcceptFromMnemonic(ctx.acceptFromMnemonicStatement());
				type = Type.MNEMONIC;
			} else if (ctx.acceptMessageCountStatement() != null) {
				result.addAcceptMessageCount(ctx.acceptMessageCountStatement());
				type = Type.MESSAGE_COUNT;
			} else if (ctx.acceptFromEscapeKeyStatement() != null) {
				result.addAcceptFromEscapeKey(ctx.acceptFromEscapeKeyStatement());
				type = Type.FROM_ESCAPE_KEY;
			} else {
				LOG.warn("unknown type at {}", ctx);
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public AddStatement addAddStatement(final AddStatementContext ctx) {
		AddStatement result = (AddStatement) getASGElement(ctx);

		if (result == null) {
			result = new AddStatementImpl(programUnit, this, ctx);

			// add sub statement
			final AddStatement.Type type;

			if (ctx.addToStatement() != null) {
				result.addAddTo(ctx.addToStatement());
				type = AddStatement.Type.TO;
			} else if (ctx.addToGivingStatement() != null) {
				result.addAddToGiving(ctx.addToGivingStatement());
				type = AddStatement.Type.GIVING;
			} else if (ctx.addCorrespondingStatement() != null) {
				result.addAddCorresponding(ctx.addCorrespondingStatement());
				type = AddStatement.Type.CORRESPONDING;
			} else {
				LOG.warn("unknown add statement at {}", ctx);
				type = null;
			}

			result.setType(type);

			// on size
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSize(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public AlterStatement addAlterStatement(final AlterStatementContext ctx) {
		AlterStatement result = (AlterStatement) getASGElement(ctx);

		if (result == null) {
			result = new AlterStatementImpl(programUnit, this, ctx);

			for (final AlterProceedToContext alterProceedToContext : ctx.alterProceedTo()) {
				result.addAlterProceedTo(alterProceedToContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CallStatement addCallStatement(final CallStatementContext ctx) {
		CallStatement result = (CallStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallStatementImpl(programUnit, this, ctx);

			// called program
			final Call programCall = createCall(ctx.literal(), ctx.identifier());
			result.setProgramCall(programCall);

			// using call by reference
			for (final CallByReferenceStatementContext callByReferenceStatementContext : ctx
					.callByReferenceStatement()) {
				result.addCallByReferenceStatement(callByReferenceStatementContext);
			}

			// using call by value
			for (final CallByValueStatementContext callByValueStatementContext : ctx.callByValueStatement()) {
				result.addCallByValueStatement(callByValueStatementContext);
			}

			// using call by content
			for (final CallByContentStatementContext callByContentStatementContext : ctx.callByContentStatement()) {
				result.addCallByContentStatement(callByContentStatementContext);
			}

			// giving
			if (ctx.callGivingPhrase() != null) {
				result.addGiving(ctx.callGivingPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflow onOverflow = createOnOverflow(ctx.onOverflowPhrase());
				result.setOnOverflow(onOverflow);
			}

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnException onException = createOnException(ctx.onExceptionClause());
				result.setOnException(onException);
			}

			// not on exception
			if (ctx.notOnExceptionClause() != null) {
				final NotOnException notOnException = createNotOnException(ctx.notOnExceptionClause());
				result.setNotOnException(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CancelStatement addCancelStatement(final CancelStatementContext ctx) {
		CancelStatement result = (CancelStatement) getASGElement(ctx);

		if (result == null) {
			result = new CancelStatementImpl(programUnit, this, ctx);

			for (final CancelCallContext cancelCallContext : ctx.cancelCall()) {
				result.addCancelCall(cancelCallContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CloseStatement addCloseStatement(final CloseStatementContext ctx) {
		CloseStatement result = (CloseStatement) getASGElement(ctx);

		if (result == null) {
			result = new CloseStatementImpl(programUnit, this, ctx);

			for (final CloseFileContext closeFileContext : ctx.closeFile()) {
				result.addCloseFile(closeFileContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ComputeStatement addComputeStatement(final ComputeStatementContext ctx) {
		ComputeStatement result = (ComputeStatement) getASGElement(ctx);

		if (result == null) {
			result = new ComputeStatementImpl(programUnit, this, ctx);

			// store calls
			for (final ComputeStoreContext computeStoreContext : ctx.computeStore()) {
				result.addStore(computeStoreContext);
			}

			// arithmetic expression
			final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
			result.setArithmeticExpression(arithmeticExpression);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeError(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ContinueStatement addContinueStatement(final ContinueStatementContext ctx) {
		ContinueStatement result = (ContinueStatement) getASGElement(ctx);

		if (result == null) {
			result = new ContinueStatementImpl(programUnit, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DeleteStatement addDeleteStatement(final DeleteStatementContext ctx) {
		DeleteStatement result = (DeleteStatement) getASGElement(ctx);

		if (result == null) {
			result = new DeleteStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			if (ctx.RECORD() != null) {
				result.setRecord(true);
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DisableStatement addDisableStatement(final DisableStatementContext ctx) {
		DisableStatement result = (DisableStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisableStatementImpl(programUnit, this, ctx);

			// type
			final DisableStatement.Type type;

			if (ctx.INPUT() != null) {
				type = DisableStatement.Type.INPUT;
			} else if (ctx.I_O() != null) {
				type = DisableStatement.Type.INPUT_OUTPUT;
			} else if (ctx.OUTPUT() != null) {
				type = DisableStatement.Type.OUTPUT;
			} else {
				type = null;
			}

			result.setType(type);

			// terminal
			if (ctx.TERMINAL() != null) {
				result.setTerminal(true);
			}

			// cd name
			final Call cdNameCall = createCall(ctx.cdName());
			result.setCommunicationDescriptionCall(cdNameCall);

			// key
			final Call keyCall = createCall(ctx.identifier(), ctx.literal());
			result.setKeyCall(keyCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(programUnit, this, ctx);

			// operands
			for (final DisplayOperandContext displayOperandContext : ctx.displayOperand()) {
				result.addOperand(displayOperandContext);
			}

			// at
			if (ctx.displayAt() != null) {
				result.addAt(ctx.displayAt());
			}

			// upon
			if (ctx.displayUpon() != null) {
				result.addUpon(ctx.displayUpon());
			}

			// with
			if (ctx.displayWith() != null) {
				result.addWith(ctx.displayWith());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DivideStatement addDivideStatement(final DivideStatementContext ctx) {
		DivideStatement result = (DivideStatement) getASGElement(ctx);

		if (result == null) {
			result = new DivideStatementImpl(programUnit, this, ctx);

			// divisor
			final Call divisorCall = createCall(ctx.identifier(), ctx.literal());
			result.setDivisorCall(divisorCall);

			// giving
			final DivideStatement.Type type;

			if (ctx.divideIntoStatement() != null) {
				result.addInto(ctx.divideIntoStatement());
				type = DivideStatement.Type.INTO;
			} else if (ctx.divideIntoGivingStatement() != null) {
				result.addIntoGiving(ctx.divideIntoGivingStatement());
				type = DivideStatement.Type.INTO_GIVING;
			} else if (ctx.divideIntoByGivingStatement() != null) {
				result.addIntoByGiving(ctx.divideIntoByGivingStatement());
				type = DivideStatement.Type.INTO_BY_GIVING;
			} else {
				type = null;
			}

			result.setType(type);

			// remainder
			if (ctx.divideRemainder() != null) {
				result.addRemainder(ctx.divideRemainder());
			}

			// on size
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeError(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EnableStatement addEnableStatement(final EnableStatementContext ctx) {
		EnableStatement result = (EnableStatement) getASGElement(ctx);

		if (result == null) {
			result = new EnableStatementImpl(programUnit, this, ctx);

			// type
			final EnableStatement.Type type;

			if (ctx.INPUT() != null) {
				type = EnableStatement.Type.INPUT;
			} else if (ctx.I_O() != null) {
				type = EnableStatement.Type.INPUT_OUTPUT;
			} else if (ctx.OUTPUT() != null) {
				type = EnableStatement.Type.OUTPUT;
			} else {
				type = null;
			}

			result.setType(type);

			// terminal
			if (ctx.TERMINAL() != null) {
				result.setTerminal(true);
			}

			// cd name
			final Call cdNameCall = createCall(ctx.cdName());
			result.setCommunicationDescriptionCall(cdNameCall);

			// key
			final Call keyCall = createCall(ctx.identifier(), ctx.literal());
			result.setKeyCall(keyCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EntryStatement addEntryStatement(final EntryStatementContext ctx) {
		EntryStatement result = (EntryStatement) getASGElement(ctx);

		if (result == null) {
			result = new EntryStatementImpl(programUnit, this, ctx);

			// entry
			final Call entryCall = createCall(ctx.literal());
			result.setEntryCall(entryCall);

			// using
			for (final IdentifierContext identifierContext : ctx.identifier()) {
				final Call usingCall = createCall(identifierContext);
				result.addUsingCall(usingCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EvaluateStatement addEvaluateStatement(final EvaluateStatementContext ctx) {
		EvaluateStatement result = (EvaluateStatement) getASGElement(ctx);

		if (result == null) {
			result = new EvaluateStatementImpl(programUnit, this, ctx);

			// select
			result.addSelect(ctx.evaluateSelect());

			// also selects
			for (final EvaluateAlsoSelectContext evaluateAlsoSelectContext : ctx.evaluateAlsoSelect()) {
				result.addAlsoSelect(evaluateAlsoSelectContext);
			}

			// when
			for (final EvaluateWhenPhraseContext evaluateWhenPhraseContext : ctx.evaluateWhenPhrase()) {
				result.addWhenPhrase(evaluateWhenPhraseContext);
			}

			// when other
			if (ctx.evaluateWhenOther() != null) {
				result.addWhenOther(ctx.evaluateWhenOther());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExhibitStatement addExhibitStatement(final ExhibitStatementContext ctx) {
		ExhibitStatement result = (ExhibitStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExhibitStatementImpl(programUnit, this, ctx);

			// operands
			for (final ExhibitOperandContext exhibitOperandContext : ctx.exhibitOperand()) {
				result.addOperand(exhibitOperandContext);
			}

			// named
			if (ctx.NAMED() != null) {
				result.setNamed(true);
			}

			// changed
			if (ctx.CHANGED() != null) {
				result.setChanged(true);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExitStatement addExitStatement(final ExitStatementContext ctx) {
		ExitStatement result = (ExitStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExitStatementImpl(programUnit, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GenerateStatement addGenerateStatement(final GenerateStatementContext ctx) {
		GenerateStatement result = (GenerateStatement) getASGElement(ctx);

		if (result == null) {
			result = new GenerateStatementImpl(programUnit, this, ctx);

			final Call reportDescriptionCall = createCall(ctx.reportName());
			result.setReportDescriptionCall(reportDescriptionCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GobackStatement addGobackStatement(final GobackStatementContext ctx) {
		GobackStatement result = (GobackStatement) getASGElement(ctx);

		if (result == null) {
			result = new GobackStatementImpl(programUnit, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GoToStatement addGoToStatement(final GoToStatementContext ctx) {
		GoToStatement result = (GoToStatement) getASGElement(ctx);

		if (result == null) {
			result = new GoToStatementImpl(programUnit, this, ctx);

			// type
			final GoToStatement.Type type;

			if (ctx.goToStatementSimple() != null) {
				result.addSimple(ctx.goToStatementSimple());
				type = GoToStatement.Type.SIMPLE;
			} else if (ctx.goToDependingOnStatement() != null) {
				result.addDependingOn(ctx.goToDependingOnStatement());
				type = GoToStatement.Type.DEPENDING_ON;
			} else {
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public IfStatement addIfStatement(final IfStatementContext ctx) {
		IfStatement result = (IfStatement) getASGElement(ctx);

		if (result == null) {
			result = new IfStatementImpl(programUnit, this, ctx);

			// condition
			final ConditionValueStmt condition = createConditionValueStmt(ctx.condition());
			result.setCondition(condition);

			// then
			if (ctx.ifThen() != null) {
				result.addThen(ctx.ifThen());
			}

			// else
			if (ctx.ifElse() != null) {
				result.addElse(ctx.ifElse());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InitializeStatement addInitializeStatement(final InitializeStatementContext ctx) {
		InitializeStatement result = (InitializeStatement) getASGElement(ctx);

		if (result == null) {
			result = new InitializeStatementImpl(programUnit, this, ctx);

			// data item calls
			for (final IdentifierContext identifierContext : ctx.identifier()) {
				final Call dataItemCall = createCall(identifierContext);
				result.addDataItemCall(dataItemCall);
			}

			// replacing
			if (ctx.initializeReplacingPhrase() != null) {
				result.addReplacing(ctx.initializeReplacingPhrase());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InitiateStatement addInitiateStatement(final InitiateStatementContext ctx) {
		InitiateStatement result = (InitiateStatement) getASGElement(ctx);

		if (result == null) {
			result = new InitiateStatementImpl(programUnit, this, ctx);

			for (final ReportNameContext reportNameContext : ctx.reportName()) {
				final Call reportCall = createCall(reportNameContext);
				result.addReportCall(reportCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InspectStatement addInspectStatement(final InspectStatementContext ctx) {
		InspectStatement result = (InspectStatement) getASGElement(ctx);

		if (result == null) {
			result = new InspectStatementImpl(programUnit, this, ctx);

			// data item call
			final Call dataItemCall = createCall(ctx.identifier());
			result.setDataItemCall(dataItemCall);

			// type
			final InspectStatement.Type type;

			if (ctx.inspectTallyingPhrase() != null) {
				result.addTallying(ctx.inspectTallyingPhrase());
				type = InspectStatement.Type.TALLYING;
			} else if (ctx.inspectReplacingPhrase() != null) {
				result.addReplacing(ctx.inspectReplacingPhrase());
				type = InspectStatement.Type.REPLACING;
			} else if (ctx.inspectTallyingReplacingPhrase() != null) {
				result.addTallyingReplacing(ctx.inspectTallyingReplacingPhrase());
				type = InspectStatement.Type.TALLYING_REPLACING;
			} else if (ctx.inspectConvertingPhrase() != null) {
				result.addConverting(ctx.inspectConvertingPhrase());
				type = InspectStatement.Type.CONVERTING;
			} else {
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MergeStatement addMergeStatement(final MergeStatementContext ctx) {
		MergeStatement result = (MergeStatement) getASGElement(ctx);

		if (result == null) {
			result = new MergeStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// on key
			for (final MergeOnKeyClauseContext mergeOnKeyClauseContext : ctx.mergeOnKeyClause()) {
				result.addOnKey(mergeOnKeyClauseContext);
			}

			// collating sequence
			if (ctx.mergeCollatingSequencePhrase() != null) {
				result.addCollatingSequence(ctx.mergeCollatingSequencePhrase());
			}

			// using
			for (final MergeUsingContext mergeUsingContext : ctx.mergeUsing()) {
				result.addUsing(mergeUsingContext);
			}

			// output procedure
			if (ctx.mergeOutputProcedurePhrase() != null) {
				result.addOutputProcedure(ctx.mergeOutputProcedurePhrase());
			}

			// giving
			for (final MergeGivingPhraseContext mergeGivingPhraseContext : ctx.mergeGivingPhrase()) {
				result.addGiving(mergeGivingPhraseContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MoveStatement addMoveStatement(final MoveStatementContext ctx) {
		MoveStatement result = (MoveStatement) getASGElement(ctx);

		if (result == null) {
			result = new MoveStatementImpl(programUnit, this, ctx);

			// type
			final MoveStatement.Type type;

			if (ctx.moveToStatement() != null) {
				type = MoveStatement.Type.MOVE_TO;
				result.addMoveTo(ctx.moveToStatement());
			} else if (ctx.moveCorrespondingToStatement() != null) {
				type = MoveStatement.Type.MOVE_CORRESPONDING_TO;
				result.addMoveCorrespondingTo(ctx.moveCorrespondingToStatement());
			} else {
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MultiplyStatement addMultiplyStatement(final MultiplyStatementContext ctx) {
		MultiplyStatement result = (MultiplyStatement) getASGElement(ctx);

		if (result == null) {
			result = new MultiplyStatementImpl(programUnit, this, ctx);

			// operand
			final Call operandCall = createCall(ctx.identifier(), ctx.literal());
			result.setOperandCall(operandCall);

			// type
			final MultiplyStatement.Type type;

			if (ctx.multiplyRegular() != null) {
				result.addRegular(ctx.multiplyRegular());
				type = MultiplyStatement.Type.REGULAR;
			} else if (ctx.multiplyGiving() != null) {
				result.addGiving(ctx.multiplyGiving());
				type = MultiplyStatement.Type.GIVING;
			} else {
				type = null;
			}

			result.setType(type);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeError(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public OpenStatement addOpenStatement(final OpenStatementContext ctx) {
		OpenStatement result = (OpenStatement) getASGElement(ctx);

		if (result == null) {
			result = new OpenStatementImpl(programUnit, this, ctx);

			// input
			for (final OpenInputStatementContext openInputStatementContext : ctx.openInputStatement()) {
				result.addOpenInput(openInputStatementContext);
			}

			// output
			for (final OpenOutputStatementContext openOutputStatementContext : ctx.openOutputStatement()) {
				result.addOpenOutput(openOutputStatementContext);
			}

			// input / output
			for (final OpenIOStatementContext openIOStatementContext : ctx.openIOStatement()) {
				result.addOpenInputOutput(openIOStatementContext);
			}

			// extend
			for (final OpenExtendStatementContext openExtendStatementContext : ctx.openExtendStatement()) {
				result.addOpenExtend(openExtendStatementContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public PerformStatement addPerformStatement(final PerformStatementContext ctx) {
		PerformStatement result = (PerformStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformStatementImpl(programUnit, this, ctx);

			// perform procedure
			if (ctx.performProcedureStatement() != null) {
				result.addPerformProcedureStatement(ctx.performProcedureStatement());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public PurgeStatement addPurgeStatement(final PurgeStatementContext ctx) {
		PurgeStatement result = (PurgeStatement) getASGElement(ctx);

		if (result == null) {
			result = new PurgeStatementImpl(programUnit, this, ctx);

			for (final CdNameContext cdNameContext : ctx.cdName()) {
				final Call cdNameCall = createCall(cdNameContext);
				result.addCommunicationDescriptionEntryCall(cdNameCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReadStatement addReadStatement(final ReadStatementContext ctx) {
		ReadStatement result = (ReadStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReadStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// next record
			if (ctx.RECORD() != null) {
				result.setNextRecord(true);
			}

			// into
			if (ctx.readInto() != null) {
				result.addInto(ctx.readInto());
			}

			// with
			if (ctx.readWith() != null) {
				result.addWith(ctx.readWith());
			}

			// key
			if (ctx.readKey() != null) {
				result.addKey(ctx.readKey());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
			}

			// at end
			if (ctx.atEndPhrase() != null) {
				final AtEnd atEnd = createAtEnd(ctx.atEndPhrase());
				result.setAtEnd(atEnd);
			}

			// not at end
			if (ctx.notAtEndPhrase() != null) {
				final NotAtEnd notAtEnd = createNotAtEnd(ctx.notAtEndPhrase());
				result.setNotAtEnd(notAtEnd);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReceiveStatement addReceiveStatement(final ReceiveStatementContext ctx) {
		ReceiveStatement result = (ReceiveStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReceiveStatementImpl(programUnit, this, ctx);

			// type
			final ReceiveStatement.Type type;

			if (ctx.receiveFromStatement() != null) {
				result.addReceiveFromStatement(ctx.receiveFromStatement());
				type = ReceiveStatement.Type.FROM;
			} else if (ctx.receiveIntoStatement() != null) {
				result.addReceiveIntoStatement(ctx.receiveIntoStatement());
				type = ReceiveStatement.Type.INTO;
			} else {
				type = null;
			}

			result.setType(type);

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnException onException = createOnException(ctx.onExceptionClause());
				result.setOnException(onException);
			}

			// not on exeption
			if (ctx.notOnExceptionClause() != null) {
				final NotOnException notOnException = createNotOnException(ctx.notOnExceptionClause());
				result.setNotOnException(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReleaseStatement addReleaseStatement(final ReleaseStatementContext ctx) {
		ReleaseStatement result = (ReleaseStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReleaseStatementImpl(programUnit, this, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// content
			final Call contentCall = createCall(ctx.qualifiedDataName());
			result.setContentCall(contentCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReturnStatement addReturnStatement(final ReturnStatementContext ctx) {
		ReturnStatement result = (ReturnStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReturnStatementImpl(programUnit, this, ctx);

			// file call
			final Call fileCall = createCall(ctx.fileName());
			result.addFileCall(fileCall);

			// into
			if (ctx.returnInto() != null) {
				result.addInto(ctx.returnInto());
			}

			// at end
			if (ctx.atEndPhrase() != null) {
				final AtEnd atEnd = createAtEnd(ctx.atEndPhrase());
				result.setAtEnd(atEnd);
			}

			// not at end
			if (ctx.notAtEndPhrase() != null) {
				final NotAtEnd notAtEnd = createNotAtEnd(ctx.notAtEndPhrase());
				result.setNotAtEnd(notAtEnd);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public RewriteStatement addRewriteStatement(final RewriteStatementContext ctx) {
		RewriteStatement result = (RewriteStatement) getASGElement(ctx);

		if (result == null) {
			result = new RewriteStatementImpl(programUnit, this, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// from
			if (ctx.rewriteFrom() != null) {
				result.addFrom(ctx.rewriteFrom());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SearchStatement addSearchStatement(final SearchStatementContext ctx) {
		SearchStatement result = (SearchStatement) getASGElement(ctx);

		if (result == null) {
			result = new SearchStatementImpl(programUnit, this, ctx);

			// data call
			final Call dataCall = createCall(ctx.qualifiedDataName());
			result.setDataCall(dataCall);

			// varying
			if (ctx.searchVarying() != null) {
				result.addVarying(ctx.searchVarying());
			}

			// at end
			if (ctx.atEndPhrase() != null) {
				final AtEnd atEnd = createAtEnd(ctx.atEndPhrase());
				result.setAtEnd(atEnd);
			}

			// when
			for (final SearchWhenContext searchWhenContext : ctx.searchWhen()) {
				result.addWhen(searchWhenContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SendStatement addSendStatement(final SendStatementContext ctx) {
		SendStatement result = (SendStatement) getASGElement(ctx);

		if (result == null) {
			result = new SendStatementImpl(programUnit, this, ctx);

			// type
			final SendStatement.Type type;

			if (ctx.sendStatementSync() != null) {
				result.addSync(ctx.sendStatementSync());
				type = SendStatement.Type.SYNC;
			} else if (ctx.sendStatementAsync() != null) {
				result.addAsync(ctx.sendStatementAsync());
				type = SendStatement.Type.ASYNC;
			} else {
				type = null;
			}

			result.setType(type);

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnException onException = createOnException(ctx.onExceptionClause());
				result.setOnException(onException);
			}

			// not on exeption
			if (ctx.notOnExceptionClause() != null) {
				final NotOnException notOnException = createNotOnException(ctx.notOnExceptionClause());
				result.setNotOnException(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SetStatement addSetStatement(final SetStatementContext ctx) {
		SetStatement result = (SetStatement) getASGElement(ctx);

		if (result == null) {
			result = new SetStatementImpl(programUnit, this, ctx);

			// type
			final SetStatement.Type type;

			if (!ctx.setToStatement().isEmpty()) {
				type = SetStatement.Type.TO;

				for (final SetToStatementContext setToStatementContext : ctx.setToStatement()) {
					result.addSetTo(setToStatementContext);
				}
			} else if (ctx.setUpDownByStatement() != null) {
				result.addSetBy(ctx.setUpDownByStatement());
				type = SetStatement.Type.BY;
			} else {
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SortStatement addSortStatement(final SortStatementContext ctx) {
		SortStatement result = (SortStatement) getASGElement(ctx);

		if (result == null) {
			result = new SortStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// on key
			for (final SortOnKeyClauseContext sortOnKeyClauseContext : ctx.sortOnKeyClause()) {
				result.addOnKey(sortOnKeyClauseContext);
			}

			// duplicates
			if (ctx.sortDuplicatesPhrase() != null) {
				result.addDuplicates(ctx.sortDuplicatesPhrase());
			}

			// collating sequence
			if (ctx.sortCollatingSequencePhrase() != null) {
				result.addCollatingSequence(ctx.sortCollatingSequencePhrase());
			}

			// using
			for (final SortUsingContext sortUsingContext : ctx.sortUsing()) {
				result.addUsing(sortUsingContext);
			}

			// input procedure
			if (ctx.sortInputProcedurePhrase() != null) {
				result.addInputProcedure(ctx.sortInputProcedurePhrase());
			}

			// giving
			for (final SortGivingPhraseContext sortGivingPhraseContext : ctx.sortGivingPhrase()) {
				result.addGiving(sortGivingPhraseContext);
			}

			// output procedure
			if (ctx.sortOutputProcedurePhrase() != null) {
				result.addOutputProcedure(ctx.sortOutputProcedurePhrase());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public StartStatement addStartStatement(final StartStatementContext ctx) {
		StartStatement result = (StartStatement) getASGElement(ctx);

		if (result == null) {
			result = new StartStatementImpl(programUnit, this, ctx);

			// file call
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// key
			if (ctx.startKey() != null) {
				result.addKey(ctx.startKey());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Statement addStatement(final StatementContext ctx) {
		// FIXME
		return null;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(programUnit, this, ctx);

			if (ctx.literal() != null) {
				final Call displayCall = createCall(ctx.literal());
				result.setDisplayCall(displayCall);
			}

			// type
			final StopStatement.Type type;

			if (ctx.RUN() != null) {
				type = StopStatement.Type.STOP_RUN;
			} else if (ctx.literal() != null) {
				type = StopStatement.Type.STOP_RUN_AND_DISPLAY;
			} else {
				LOG.warn("unknown type at {}", ctx);
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public StringStatement addStringStatement(final StringStatementContext ctx) {
		StringStatement result = (StringStatement) getASGElement(ctx);

		if (result == null) {
			result = new StringStatementImpl(programUnit, this, ctx);

			// sending
			for (final StringSendingPhraseContext stringSendingPhraseContext : ctx.stringSendingPhrase()) {
				result.addSendings(stringSendingPhraseContext);
			}

			// into
			result.addInto(ctx.stringIntoPhrase());

			// with pointer
			if (ctx.stringWithPointerPhrase() != null) {
				result.addWithPointer(ctx.stringWithPointerPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflow onOverflow = createOnOverflow(ctx.onOverflowPhrase());
				result.setOnOverflow(onOverflow);
			}

			// not on overflow
			if (ctx.notOnOverflowPhrase() != null) {
				final NotOnOverflow notOnOverflow = createNotOnOverflow(ctx.notOnOverflowPhrase());
				result.setNotOnOverflow(notOnOverflow);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SubtractStatement addSubtractStatement(final SubtractStatementContext ctx) {
		SubtractStatement result = (SubtractStatement) getASGElement(ctx);

		if (result == null) {
			result = new SubtractStatementImpl(programUnit, this, ctx);

			// type
			final SubtractStatement.Type type;

			if (ctx.subtractFromStatement() != null) {
				result.addSubtractFrom(ctx.subtractFromStatement());
				type = SubtractStatement.Type.FROM;
			} else if (ctx.subtractFromGivingStatement() != null) {
				result.addSubtractFromGiving(ctx.subtractFromGivingStatement());
				type = SubtractStatement.Type.FROM_GIVING;
			} else if (ctx.subtractCorrespondingStatement() != null) {
				result.addSubtractCorresponding(ctx.subtractCorrespondingStatement());
				type = SubtractStatement.Type.CORRESPONDING;
			} else {
				type = null;
			}

			result.setType(type);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeError(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public TerminateStatement addTerminateStatement(final TerminateStatementContext ctx) {
		TerminateStatement result = (TerminateStatement) getASGElement(ctx);

		if (result == null) {
			result = new TerminateStatementImpl(programUnit, this, ctx);

			final Call reportCall = createCall(ctx.reportName());
			result.setReportCall(reportCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public UnstringStatement addUnstringStatement(final UnstringStatementContext ctx) {
		UnstringStatement result = (UnstringStatement) getASGElement(ctx);

		if (result == null) {
			result = new UnstringStatementImpl(programUnit, this, ctx);

			// sending
			result.addSending(ctx.unstringSendingPhrase());

			// into
			result.addIntos(ctx.unstringIntoPhrase());

			// with pointer
			if (ctx.unstringWithPointerPhrase() != null) {
				result.addWithPointer(ctx.unstringWithPointerPhrase());
			}

			// tallying
			if (ctx.unstringTallyingPhrase() != null) {
				result.addTallying(ctx.unstringTallyingPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflow onOverflow = createOnOverflow(ctx.onOverflowPhrase());
				result.setOnOverflow(onOverflow);
			}

			// not on overflow
			if (ctx.notOnOverflowPhrase() != null) {
				final NotOnOverflow notOnOverflow = createNotOnOverflow(ctx.notOnOverflowPhrase());
				result.setNotOnOverflow(notOnOverflow);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public WriteStatement addWriteStatement(final WriteStatementContext ctx) {
		WriteStatement result = (WriteStatement) getASGElement(ctx);

		if (result == null) {
			result = new WriteStatementImpl(programUnit, this, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// from
			if (ctx.writeFromPhrase() != null) {
				result.addFrom(ctx.writeFromPhrase());
			}

			// advancing
			if (ctx.writeAdvancingPhrase() != null) {
				result.addAdvancing(ctx.writeAdvancingPhrase());
			}

			// at end of page
			if (ctx.writeAtEndOfPagePhrase() != null) {
				result.addAtEndOfPage(ctx.writeAtEndOfPagePhrase());
			}

			// not at end of page
			if (ctx.writeNotAtEndOfPagePhrase() != null) {
				result.addNotAtEndOfPage(ctx.writeNotAtEndOfPagePhrase());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	protected AtEnd createAtEnd(final AtEndPhraseContext ctx) {
		AtEnd result = (AtEnd) getASGElement(ctx);

		if (result == null) {
			result = new AtEndImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected InvalidKey createInvalidKey(final InvalidKeyPhraseContext ctx) {
		InvalidKey result = (InvalidKey) getASGElement(ctx);

		if (result == null) {
			result = new InvalidKeyImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotAtEnd createNotAtEnd(final NotAtEndPhraseContext ctx) {
		NotAtEnd result = (NotAtEnd) getASGElement(ctx);

		if (result == null) {
			result = new NotAtEndImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotInvalidKey createNotInvalidKey(final NotInvalidKeyPhraseContext ctx) {
		NotInvalidKey result = (NotInvalidKey) getASGElement(ctx);

		if (result == null) {
			result = new NotInvalidKeyImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnException createNotOnException(final NotOnExceptionClauseContext ctx) {
		NotOnException result = (NotOnException) getASGElement(ctx);

		if (result == null) {
			result = new NotOnExceptionImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnOverflow createNotOnOverflow(final NotOnOverflowPhraseContext ctx) {
		NotOnOverflow result = (NotOnOverflow) getASGElement(ctx);

		if (result == null) {
			result = new NotOnOverflowImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnSizeError createNotOnSizeError(final NotOnSizeErrorPhraseContext ctx) {
		NotOnSizeError result = (NotOnSizeError) getASGElement(ctx);

		if (result == null) {
			result = new NotOnSizeErrorImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected OnException createOnException(final OnExceptionClauseContext ctx) {
		OnException result = (OnException) getASGElement(ctx);

		if (result == null) {
			result = new OnExceptionImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected OnOverflow createOnOverflow(final OnOverflowPhraseContext ctx) {
		OnOverflow result = (OnOverflow) getASGElement(ctx);

		if (result == null) {
			result = new OnOverflowImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected OnSizeError createOnSizeError(final OnSizeErrorPhraseContext ctx) {
		OnSizeError result = (OnSizeError) getASGElement(ctx);

		if (result == null) {
			result = new OnSizeErrorImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}

	protected void registerStatement(final Statement statement) {
		assert statement != null;
		assert statement.getCtx() != null;

		registerASGElement(statement);
		statements.add(statement);
	}

}
