package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ContaTest {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar(){
        contaValida = new Conta(1, cem, "Rebeca");
    }

    @Test
    @DisplayName("Verifica se usuário tenta creditar um valor nulo")
    void verificaCreditoNulo(){
        try {
            contaValida.creditar(null);
            Assert.fail("valor crédito obrigatório.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Valor crédito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta creditar um valor negativo")
    void verificaCreditoNegativo(){
        try {
            contaValida.creditar(new BigDecimal(-10));
            Assert.fail("valor crédito obrigatório.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Valor crédito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta creditar um valor igual a zero")
    void verificaCreditoZero(){
        try {
            contaValida.creditar(new BigDecimal(0));
            Assert.fail("valor crédito obrigatório.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Valor crédito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta creditar um valor acima de zero")
    void verificaCreditoCorreto(){
        try {
            contaValida.creditar(BigDecimal.ONE);
            BigDecimal valorFinal = cem.add(BigDecimal.ONE);
            Assert.assertEquals(contaValida.getSaldo(), valorFinal);
        } catch (NegocioException e){
            Assert.fail("Deve creditar com sucesso - " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta debitar um valor nulo")
    void verificaDebitoNulo(){
        try {
            contaValida.debitar(null);
            Assert.fail("valor débito obrigatório.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Valor débito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta debitar um valor negativo")
    void verificaDebitoNegativo(){
        try {
            contaValida.debitar(new BigDecimal(-10));
            Assert.fail("valor débito obrigatório.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Valor débito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta debitar um valor igual a zero")
    void verificaDebitoZero(){
        try {
            contaValida.debitar(BigDecimal.ZERO);
            Assert.fail("valor débito obrigatório.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Valor débito é obrigatório!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta debitar um valor maior que seu saldo")
    void verificaDebitoInsuficiente(){
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            Assert.fail("valor débito maior que saldo.");
        } catch (NegocioException e){
            Assert.assertEquals(e.getMessage(), "Saldo insuficiente!");
        }
    }

    @Test
    @DisplayName("Verifica se usuário tenta debitar um valor menor que seu saldo")
    void verificaDebitoCorreto(){
        try {
            contaValida.debitar(new BigDecimal(20));
            BigDecimal valorFinal = cem.subtract(new BigDecimal(20));
            Assert.assertEquals(contaValida.getSaldo(), valorFinal);
        } catch (NegocioException e){
            Assert.fail("Deve creditar com sucesso - " + e.getMessage());
        }
    }
}
