/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames;

import io.proleap.cobol.Cobol85Parser.ChannelClauseContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseContext;
import io.proleap.cobol.Cobol85Parser.CurrencySignClauseContext;
import io.proleap.cobol.Cobol85Parser.DecimalPointClauseContext;
import io.proleap.cobol.Cobol85Parser.DefaultDisplaySignClauseContext;
import io.proleap.cobol.Cobol85Parser.OdtClauseContext;
import io.proleap.cobol.Cobol85Parser.ReserveNetworkClauseContext;
import io.proleap.cobol.Cobol85Parser.SymbolicCharactersClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;

public interface SpecialNamesParagraph extends CobolDivisionElement {

	ChannelClause addChannelClause(ChannelClauseContext ctx);

	ClassClause addClassClause(ClassClauseContext ctx);

	CurrencySignClause addCurrencySignClause(CurrencySignClauseContext ctx);

	DecimalPointClause addDecimalPointClause(DecimalPointClauseContext ctx);

	DefaultDisplaySignClause addDefaultDisplaySignClause(DefaultDisplaySignClauseContext ctx);

	OdtClause addOdtClause(OdtClauseContext ctx);

	ReserveNetworkClause addReserveNetworkClause(ReserveNetworkClauseContext ctx);

	SymbolicCharactersClause addSymbolicCharactersClause(SymbolicCharactersClauseContext ctx);

	ChannelClause getChannelClause();

	ClassClause getClassClause();

	CurrencySignClause getCurrencySignClause();

	DecimalPointClause getDecimalPointClause();

	DefaultDisplaySignClause getDefaultDisplaySignClause();

	OdtClause getOdtClause();

	ReserveNetworkClause getReserveNetworkClause();

	SymbolicCharactersClause getSymbolicCharactersClause();
}
