/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use;

import io.proleap.cobol.Cobol85Parser.UseAfterClauseContext;
import io.proleap.cobol.Cobol85Parser.UseDebugClauseContext;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Specifies procedures for handling input or output errors.
 */
public interface UseStatement extends Statement {

	enum Type {
		AFTER, DEBUG
	}

	After addAfter(UseAfterClauseContext ctx);

	Debug addDebug(UseDebugClauseContext ctx);

	After getAfter();

	Debug getDebug();

	Type getType();

	void setType(Type type);
}
