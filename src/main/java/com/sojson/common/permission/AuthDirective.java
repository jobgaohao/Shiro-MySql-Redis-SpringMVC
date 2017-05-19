package com.sojson.common.permission;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sojson.common.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


/**
 * 自定义权限表
 * <pre>
 * 
 * </pre>
 *
 * @author hao.gao
 * @version $Id: AuthDirective.java, v 0.1 2017年5月19日 下午2:36:53 hao.gao Exp $
 */
public class AuthDirective implements TemplateDirectiveModel {

    private Logger log=LoggerFactory.getLogger(AuthDirective.class) ;
    
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
      String code=params.get("code").toString();      
      log.info("权限code:{}",code);
      if(code==null||StringUtils.isBlank(code.toString())){
          log.error("@auth需要参数：权限编码Code");
          return;
      }
      
      if(code.equals("hi")){
          body.render(env.getOut());
      }
      else{
          log.info("无权限，请检查");
      }
    }

}
