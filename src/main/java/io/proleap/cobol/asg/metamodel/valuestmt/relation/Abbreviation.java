/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.relation;

import io.proleap.cobol.Cobol85Parser.AbbreviationContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;

public interface Abbreviation extends ComparisonValueStmt {

	Abbreviation addAbbreviation(AbbreviationContext ctx);

	Abbreviation getAbbreviation();

	ArithmeticValueStmt getArithmeticExpression();

	@Override
	String getValue();

	void setArithmeticExpression(ArithmeticValueStmt arithmeticExpression);

}
