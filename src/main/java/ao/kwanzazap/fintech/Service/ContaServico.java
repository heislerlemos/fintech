package ao.kwanzazap.fintech.Service;

import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class ContaServico {
    @Autowired

    private ContaRepository contaRepository;


    public Conta criarconta (Conta conta){
        return contaRepository.save(conta);
    }

    public Optional <Conta> getConta (Long id ) {
        return contaRepository.findById(id);
    }


    public Conta deposito (Long id, double valor ){
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setBalanco(conta.getBalanco() + valor );
        return contaRepository.save(conta);
    }

    public Conta levantamento (Long id , double valor) {
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (conta.getBalanco() < valor ){
            throw new RuntimeException("Valores insuficientes");

        }
        conta.setBalanco(conta.getBalanco() - valor );
        return contaRepository.save(conta);
    }

}
