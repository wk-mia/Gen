package ${nameSpace}.${domainName};

import ${nameSpace}.domain.${domainName};
import com.mia.templete.bu.util.SwResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Add your relevant content description here.
 * @auther ${author}
 * @create ${createDate}
 */
@RestController
@RequestMapping(value = "/${domainName}")
public class ${domainNameUpper}Controller{
    @Autowired
    private ${domainNameUpper}Service ${domainNameLower}Service;



}
