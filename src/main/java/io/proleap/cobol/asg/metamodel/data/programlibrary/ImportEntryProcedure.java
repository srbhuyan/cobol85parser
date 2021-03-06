/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureForClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureGivingClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureUsingClauseContext;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureWithClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ImportEntryProcedure extends CobolDivisionElement {

	ForClause addForClause(LibraryEntryProcedureForClauseContext ctx);

	GivingClause addGivingClause(LibraryEntryProcedureGivingClauseContext ctx);

	UsingClause addUsingClause(LibraryEntryProcedureUsingClauseContext ctx);

	WithClause addWithClause(LibraryEntryProcedureWithClauseContext ctx);

	ForClause getForClause();

	GivingClause getGivingClause();

	Call getProgramCall();

	UsingClause getUsingClause();

	WithClause getWithClause();

	void setProgramCall(Call programCall);
}
