package urfu.emelianov.dto;

import lombok.Getter;

/**
 * Перечисление {@code Country} представляет различные страны.
 *
 * <p>Перечисление содержит три элемента: {@code RUS} (Россия), {@code USA} (Соединенные Штаты Америки)
 * и {@code ITA} (Италия), представляющие соответствующие страны.
 */
@Getter
public enum Country {
    RUS,
    USA,
    ITA
}
