/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Literal;

public interface ImportAttribute extends CobolDivisionElement {

	enum Type {
		BY_FUNCTION, BY_TITLE
	}

	Literal getFunctionLiteral();

	Literal getParameterLiteral();

	Literal getTitleLiteral();

	Type getType();

	void setFunctionLiteral(Literal functionLiteral);

	void setParameterLiteral(Literal parameterLiteral);

	void setTitleLiteral(Literal titleLiteral);

	void setType(Type type);
}
