package org.kie.formModeler.backend.sourceGeneration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.bus.server.api.RpcContext;
import org.kie.formModeler.common.model.FormDefinition;
import org.kie.formModeler.generation.java.JavaFormSourceGenerator;
import org.kie.formModeler.shared.sourceGeneration.FormSourceCreator;

/**
 * Created by pefernan on 3/24/15.
 */
@Service
@ApplicationScoped
public class FormSourceCreatorImpl implements FormSourceCreator {

    @Inject
    private JavaFormSourceGenerator javaFormSourceGenerator;


    @Override
    public void createFormSource( FormDefinition definition, String htmlTemplate ) {
        String source = javaFormSourceGenerator.generateSource( definition );
        String rootDir = RpcContext.getHttpSession().getServletContext().getRealPath(File.separator);
        String basePath = rootDir + "/app1/src/main/java/org/kie/formModeler/examples/client/auto";
        String fileName = WordUtils.capitalize( definition.getName() );
        try {

            File javaFile = new File(basePath + "/" + fileName + ".java");

            if ( !javaFile.getParentFile().exists() ) javaFile.getParentFile().mkdirs();

            if ( javaFile.exists() ) javaFile.delete();

            javaFile.createNewFile();

            IOUtils.write( source.getBytes(), new FileOutputStream( javaFile ) );

            File htmlFile = new File(basePath + "/" + fileName + ".html");

            if ( htmlFile.exists() ) htmlFile.delete();

            htmlFile.createNewFile();

            IOUtils.write( htmlTemplate.trim().getBytes(), new FileOutputStream( htmlFile ) );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
