import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sojson.common.model.UUser;
import com.sojson.permission.bo.URoleBo;
import com.sojson.user.service.UUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UuserServiceTest {
	
	@Resource
	private UUserService service;
	
	/**
	 * 根据主键ID 查询用户信息
	 */
    @Test
    public void selUserByPrimaryKey() {
    	UUser user = service.selectByPrimaryKey(12l);
    	System.out.println("==============>"+user.toString());
    }
    
    /**
     * 根据用户ID获取用户角色
     */
    @Test
    public void selRoleByUserId(){
    	List<URoleBo> roles= service.selectRoleByUserId(12l);
    	for (URoleBo uRoleBo : roles) {
			System.out.println(uRoleBo.toString());
		}
    }
}
