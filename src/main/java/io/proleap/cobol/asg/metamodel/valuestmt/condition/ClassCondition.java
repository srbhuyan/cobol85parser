/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface ClassCondition extends ValueStmt {

	enum Type {
		ALPHABETIC, ALPHABETIC_LOWER, ALPHABETIC_UPPER, CLASS_NAME, DBCS, KANJI, NUMERIC
	}

	Call getClassCall();

	Call getIdentifierCall();

	boolean getNot();

	Type getType();

	@Override
	String getValue();

	void setClassCall(Call classCall);

	void setIdentifierCall(Call identifierCall);

	void setNot(boolean not);

	void setType(Type type);

}
