package com.zzami.alarm.api.repository;

import java.util.List;
import com.zzami.alarm.api.dto.NewsMetaTagDTO;
import com.zzami.alarm.api.entity.NewsInfo;

public interface NewsInfoRepository extends BaseRepository<NewsInfo, Long> {

    public List<NewsMetaTagDTO> getCurrentNewsInfoMeta(List<String> metaCodeList) ;
}
