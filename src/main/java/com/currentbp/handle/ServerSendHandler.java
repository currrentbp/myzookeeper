package com.currentbp.handle;

import com.alibaba.fastjson.JSON;
import com.currentbp.agreement.BaseAgreement;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author baopan
 * @createTime 20210119
 */
public class ServerSendHandler extends BaseServerHandler {

    /**
     * 本方法用于读取客户端发送的信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("SimpleNettyServerHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] bytesMsg = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(bytesMsg);
        String resultStr = new String(bytesMsg);
        // 接收并打印客户端的信息
        System.out.println("Client said:" + resultStr);
        // 释放资源，这行很关键
        result.release();

        BaseAgreement responseAgreement = JSON.parseObject(resultStr, BaseAgreement.class);
        responseAgreement.setOriginalId(responseAgreement.getId());
        responseAgreement.setId("2");
        responseAgreement.setType(1);
        responseAgreement.setBody(responseAgreement.getBody()+" ,i say yes!");
        // 向客户端发送消息
        String response = JSON.toJSONString(responseAgreement);
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
        ctx.close();
    }
}
