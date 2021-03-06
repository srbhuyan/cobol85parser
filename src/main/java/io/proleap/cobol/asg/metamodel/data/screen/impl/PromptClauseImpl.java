/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionPromptClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionPromptOccursClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.screen.Occurs;
import io.proleap.cobol.asg.metamodel.data.screen.PromptClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class PromptClauseImpl extends CobolDivisionElementImpl implements PromptClause {

	protected Call characterCall;

	protected ScreenDescriptionPromptClauseContext ctx;

	protected Occurs occurs;

	public PromptClauseImpl(final ProgramUnit programUnit, final ScreenDescriptionPromptClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Occurs addOccurs(final ScreenDescriptionPromptOccursClauseContext ctx) {
		Occurs result = (Occurs) getASGElement(ctx);

		if (result == null) {
			result = new OccursImpl(programUnit, ctx);

			final IntegerLiteral occursTimes = createIntegerLiteral(ctx.integerLiteral());
			result.setOccursTimes(occursTimes);

			occurs = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getCharacterCall() {
		return characterCall;
	}

	@Override
	public Occurs getOccurs() {
		return occurs;
	}

	@Override
	public void setCharacterCall(final Call characterCall) {
		this.characterCall = characterCall;
	}

}
