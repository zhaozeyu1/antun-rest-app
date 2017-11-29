package com.zdjy.bigdata.antun.service;

import com.zdjy.bigdata.antun.domain.Channel;

public interface ChannelService {

	Channel findByChannelCode(String channelCode);
}
