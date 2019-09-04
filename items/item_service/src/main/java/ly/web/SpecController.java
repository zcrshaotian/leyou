package ly.web;

import ly.domain.SpecGroup;
import ly.domain.SpecParams;
import ly.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    //根据分类id查询出所有规格参数分组的组名称
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> findGroups(@PathVariable("cid")Long cid){
        return ResponseEntity.ok(specService.findGroup(cid));
    }

    //根据组id或分类id查询该组下的规格参数名称
    @GetMapping("params")
    public ResponseEntity<List<SpecParams>> findSpecParams(@RequestParam(name = "gid", required = false)Long gid,
                                                           @RequestParam(name = "cid", required = false)Long cid,
                                                           @RequestParam(name = "searching", required = false)Boolean searching){
        return ResponseEntity.ok(specService.findSpecParams(gid, cid, searching));
    }

}
