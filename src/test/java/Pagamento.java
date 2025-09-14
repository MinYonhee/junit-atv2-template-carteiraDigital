import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;


public class Pagamento {
    DigitalWallet digitalWallet;

    @ParameterizedTest
    @CsvSource({
            "100.0, 30.0, true",
            "50.0, 80.0, false",
            "10.0, 10.0, true"
    })
    void pagamentoComCarteiraVerificadaENaoBloqueada(double inicial, double valor, boolean esperado) {
        digitalWallet = new DigitalWallet("Bea", inicial);

        digitalWallet.unlock();
        digitalWallet.verify();

        assumeTrue(!digitalWallet.isLocked());
        assumeTrue(digitalWallet.isVerified());

        boolean result = digitalWallet.pay(valor);
        Assertions.assertEquals(esperado, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0})
    void deveLancarExcecaoParaPagamentoInvalido(double valor) {
        digitalWallet = new DigitalWallet("Bea", 500);
        digitalWallet.unlock();
        digitalWallet.verify();

        assumeTrue(!digitalWallet.isLocked());
        assumeTrue(digitalWallet.isVerified());


        assertThrows(IllegalArgumentException.class, () -> digitalWallet.pay(valor), "Pagamentos serão apenas realizados mediante valores válidos");
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        digitalWallet = new DigitalWallet("Bea", 90000);
        assertThrows(IllegalStateException.class, () -> digitalWallet.pay(10));
    }
}
