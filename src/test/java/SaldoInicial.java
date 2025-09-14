import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SaldoInicial {
        DigitalWallet digitalWallet;

        @Test
        void deveConfigurarSaldoInicialCorreto() {
            digitalWallet = new DigitalWallet("Bea", 90000);

            assertEquals(90000, digitalWallet.getBalance());
        }

        @Test
        public void deveLancarExcecaoParaSaldoInicialNegativo() {
            assertThrows(IllegalArgumentException.class, () -> new DigitalWallet("Bea", -9), "Deve conter um valor válido para inicializar a classe");
        }
    }