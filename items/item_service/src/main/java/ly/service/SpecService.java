package ly.service;

import ly.Exception.LyException;
import ly.dao.SpecDao;
import ly.dao.SpecParamDao;
import ly.domain.SpecGroup;
import ly.domain.SpecParams;
import ly.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecService {

    @Autowired
    private SpecDao specDao;

    @Autowired
    private SpecParamDao specParamDao;

    public List<SpecGroup> findGroup(Long cid){
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> groups = specDao.select(specGroup);
        if (CollectionUtils.isEmpty(groups)){
            throw new LyException(ExceptionEnum.SpecGroups_Not_Found);
        }
        return groups;
    }

    public List<SpecParams> findSpecParams(Long gid, Long cid, Boolean searching) {
        SpecParams params = new SpecParams();
        params.setCid(cid);
        params.setGid(gid);
        params.setSearching(searching);
        List<SpecParams> specParams = specParamDao.select(params);
        if (CollectionUtils.isEmpty(specParams)){
            throw new LyException((ExceptionEnum.SpecParams_Not_Found));
        }
        return specParams;
    }
}
