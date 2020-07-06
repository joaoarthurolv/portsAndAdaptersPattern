package teste.unidade.dominio.servico;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.dominio.servico.Transferencia;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TransferenciaTest {

    BigDecimal cem = new BigDecimal(100);
    BigDecimal vinte = new BigDecimal(20);
    Transferencia transferencia;
    Conta contaDebito;
    Conta contaCredito;

    @BeforeEach
    void preparar(){
        contaDebito = new Conta(1, cem, "Fernando");
        contaCredito = new Conta(2, cem, "Rebeca");
        transferencia = new Transferencia();
    }

    @Test
    @DisplayName("Verifica se valor da transferência é nulo")
    void verificaValorNulo(){
        try{
            transferencia.transferencia(null, contaDebito, contaCredito);
            Assert.fail("Valor crédito obrigatório");
        } catch (NegocioException e) {
            Assert.assertEquals(e.getMessage(), "Valor de transferência é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica conta débito como obrigatória")
    void verificaContaDebitoObrigatoria(){
        try{
            transferencia.transferencia(vinte, null, contaCredito);
            Assert.fail("Conta débito obrigatória");
        } catch (NegocioException e) {
            Assert.assertEquals(e.getMessage(), "Conta débito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica conta crédito como obrigatória")
    void verificaContaCreditoObrigatoria(){
        try{
            transferencia.transferencia(vinte, contaDebito, null);
            Assert.fail("Conta crédito obrigatória");
        } catch (NegocioException e) {
            Assert.assertEquals(e.getMessage(), "Conta crédito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica uma transferência correta")
    void verificaTransferenciaCorreta(){
        try{
            transferencia.transferencia(vinte, contaDebito, contaCredito);
            Assert.assertEquals(contaDebito.getSaldo(), cem.subtract(vinte));
            Assert.assertEquals(contaCredito.getSaldo(), cem.add(vinte));
        } catch (NegocioException e) {
            Assert.fail("Deve transferir com sucesso!");
        }
    }
}
