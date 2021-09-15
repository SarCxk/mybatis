package com.scy.bean;

import java.io.Serializable;

/**
 * account
 * @author 
 */
public class Account implements Serializable {
    private Integer accountId;

    private String accountNum;

    private String accountPwd;

    private String accountRealName;

    private static final long serialVersionUID = 1L;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public String getAccountRealName() {
        return accountRealName;
    }

    public void setAccountRealName(String accountRealName) {
        this.accountRealName = accountRealName;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Account other = (Account) that;
        return (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountNum() == null ? other.getAccountNum() == null : this.getAccountNum().equals(other.getAccountNum()))
            && (this.getAccountPwd() == null ? other.getAccountPwd() == null : this.getAccountPwd().equals(other.getAccountPwd()))
            && (this.getAccountRealName() == null ? other.getAccountRealName() == null : this.getAccountRealName().equals(other.getAccountRealName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAccountNum() == null) ? 0 : getAccountNum().hashCode());
        result = prime * result + ((getAccountPwd() == null) ? 0 : getAccountPwd().hashCode());
        result = prime * result + ((getAccountRealName() == null) ? 0 : getAccountRealName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountId=").append(accountId);
        sb.append(", accountNum=").append(accountNum);
        sb.append(", accountPwd=").append(accountPwd);
        sb.append(", accountRealName=").append(accountRealName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}