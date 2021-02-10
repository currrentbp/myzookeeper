package com.currentbp.agreement;

import com.alibaba.fastjson.JSON;
import com.currentbp.cache.MyKVCache;

/**
 * 处理KV的协议
 *
 * @author baopan
 * @createTime 20210210
 */
public class HandleKVAgreementStrategy {

    public BaseAgreement handleKV(BaseAgreement baseAgreement) {
        String id = baseAgreement.getId();
        String body = baseAgreement.getBody();
        KVPermanentSaveAgreement originalKV = JSON.parseObject(body, KVPermanentSaveAgreement.class);

        String key = originalKV.getKey();
        String value = originalKV.getValue();

        int type = originalKV.getType();
        KVPermanentSaveAgreementConstants.KVAgreementType kvAgreementType = KVPermanentSaveAgreementConstants.KVAgreementType.valueOf(type);
        if (KVPermanentSaveAgreementConstants.KVAgreementType.PULL == kvAgreementType) {
            String value2 = MyKVCache.get(key);
        } else if (KVPermanentSaveAgreementConstants.KVAgreementType.PUSH == kvAgreementType) {
            MyKVCache.set(key, value);
        }

        BaseAgreement result = new BaseAgreement();
        result.setOriginalId(id);
        result.setRetCode("success");
        result.setId(id + "_1");
        return result;
    }
}
