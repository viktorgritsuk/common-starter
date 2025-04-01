package com.common.starter.conversion.converterfactory;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;

import com.common.starter.model.enums.StringInterpretedEnum;

/**
 * Replace default Spring enum to string converter factory.
 * <p>
 * It needs for adding
 * additional logic for request params and request body field conversion, which are represented as
 * enum type. All conversation for enums, which implemented StringValuedEnum interface are making
 * by enums 'value' field value. Other default enum are converting in usual way.
 * </p>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> @NotNull Converter<String, T> getConverter(@NotNull Class<T> targetType) {
        if (StringInterpretedEnum.class.isAssignableFrom(targetType)) {
            return new ValuedStringToEnumConverter(targetType);
        }

        return new DefaultStringToEnumConverter(getEnumType(targetType));
    }

    /**
     * @param enumType Enum type class.
     */
    private record DefaultStringToEnumConverter<T extends Enum>(Class<T> enumType) implements Converter<String, T> {

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }

            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }

    /**
     * @param enumType Enum type class.
     */
    private record ValuedStringToEnumConverter<T extends Enum>(Class<T> enumType) implements Converter<String, T> {

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }

            for (T enumConstant : enumType.getEnumConstants()) {
                if (((StringInterpretedEnum) enumConstant).getStringInterpretedValue().equals(source)) {
                    return enumConstant;
                }
            }

            throw new IllegalArgumentException(
                "No enum constant " + enumType.getCanonicalName() + "." + source);
        }
    }

    private Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType;

        if (targetType != null && !targetType.isEnum()) {
            enumType = targetType.getSuperclass();
        }
        else {
            enumType = targetType;
        }

        return enumType;
    }

}
