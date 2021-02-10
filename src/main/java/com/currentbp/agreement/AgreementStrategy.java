package com.currentbp.agreement;

/**
 * @author baopan
 * @createTime 20210210
 */
public class AgreementStrategy {

    public BaseAgreement handle(BaseAgreement baseAgreement){
        AgreementConstants.AgreementType agreementType = AgreementConstants.AgreementType.valueOf(baseAgreement.getType());
        switch (agreementType){
            case KV:
                return new HandleKVAgreementStrategy().handleKV(baseAgreement);
        }

        return null;
    }
}
