/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface With extends CobolDivisionElement {

	enum Type {
		CALL, EGI, EMI, ESI
	}

	Type getType();

	Call getWithCall();

	void setType(Type type);

	void setWithCall(Call withCall);

}
