package io.micronaut.docs.expressions;

import jakarta.inject.Singleton;
import java.security.SecureRandom;
import java.util.Random;

@Singleton
public class CustomEvaluationContext {
    public int generateRandom(int min, int max) {
        return new SecureRandom().nextInt(max - min) + min;
    }
}
