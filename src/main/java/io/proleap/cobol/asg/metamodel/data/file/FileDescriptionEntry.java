/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.BlockContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.CodeSetClauseContext;
import io.proleap.cobol.Cobol85Parser.DataRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.ExternalClauseContext;
import io.proleap.cobol.Cobol85Parser.GlobalClauseContext;
import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.LinageClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportClauseContext;
import io.proleap.cobol.Cobol85Parser.ValueOfClauseContext;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.FileDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;

public interface FileDescriptionEntry extends DataDescriptionEntryContainer, Declaration {

	BlockContainsClause addBlockContainsClause(BlockContainsClauseContext ctx);

	void addCall(FileDescriptionEntryCall call);

	CodeSetClause addCodeSetClause(CodeSetClauseContext ctx);

	DataRecordsClause addDataRecordsClause(DataRecordsClauseContext ctx);

	ExternalClause addExternalClause(ExternalClauseContext ctx);

	GlobalClause addGlobalClause(GlobalClauseContext ctx);

	LabelRecordsClause addLabelRecordsClause(LabelRecordsClauseContext ctx);

	LinageClause addLinageClause(LinageClauseContext ctx);

	RecordContainsClause addRecordContainsClause(RecordContainsClauseContext ctx);

	ReportClause addReportClause(ReportClauseContext ctx);

	ValueOfClause addValueOfClause(ValueOfClauseContext ctx);

	BlockContainsClause getBlockContainsClause();

	List<FileDescriptionEntryCall> getCalls();

	CodeSetClause getCodeSetClause();

	DataRecordsClause getDataRecordsClause();

	ExternalClause getExternalClause();

	GlobalClause getGlobalClause();

	LabelRecordsClause getLabelRecordsClause();

	LinageClause getLinageClause();

	RecordContainsClause getRecordContainsClause();

	ReportClause getReportClause();

	ValueOfClause getValueOfClause();

}
