package com.springbook.biz.board;

import java.sql.Date;

public class ReplyVO {
	private int replySeq;
	private String replyId;
	private String replyType;
	private String replyContent;
	private String replyWriter;
	private Date replyUpdate;
	private Date replyRegdate;

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public int getReplySeq() {
		return replySeq;
	}

	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getReplyUpdate() {
		return replyUpdate;
	}

	public void setReplyUpdate(Date replyUpdate) {
		this.replyUpdate = replyUpdate;
	}

	public Date getReplyRegdate() {
		return replyRegdate;
	}

	public void setReplyRegdate(Date replyRegdate) {
		this.replyRegdate = replyRegdate;
	}

	@Override
	public String toString() {
		return "ReplyVO [replySeq=" + replySeq + ", replyId=" + replyId + ", replyType=" + replyType + ", replyContent="
				+ replyContent + ", replyWriter=" + replyWriter + ", replyUpdate=" + replyUpdate + ", replyRegdate="
				+ replyRegdate + "]";
	}

}
