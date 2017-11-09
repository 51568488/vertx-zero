package io.vertx.up.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("io.vertx.up.annotations.Routine")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class RoutineProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotation,
                           final RoundEnvironment roundEnv) {
        System.out.println("System");
        return true;
    }
}
