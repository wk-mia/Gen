package ${nameSpace}.${domainName};

import ${nameSpace}.domain.${domainName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description Add your relevant content description here.
 * @auther ${author}
 * @create ${createDate}
 */
@Service
public class ${domainNameUpper}ServiceImp implements ${domainNameUpper}Service{

    @Autowired
    private ${domainNameUpper}Dao ${domainNameLower}Dao;



}
