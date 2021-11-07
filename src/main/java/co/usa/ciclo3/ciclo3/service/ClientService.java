package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es el servicio de Client
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Este metodo obtiene toda la lista de clients
     * @return
     */
    public List<Client> getALL(){
        return  clientRepository.getALL();
    }

    /**
     * Este metodo obtiene un Client por Id
     * @param idClient
     * @return
     */
    public Optional<Client> getClient(int idClient){
        return clientRepository.getClient(idClient);
    }

    /**
     * Este metodo guarda un Client
     * @param client
     * @return
     */
    public Client save(Client client){
        if (client.getIdClient()==null){
            return clientRepository.save(client);
        }else{
            Optional<Client> caux = clientRepository.getClient(client.getIdClient());
            if (caux.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
    }

    /**
     * Este metodo actualiza un Client
     * @param client
     * @return
     */
    public Client update(Client client){
        if (client.getIdClient() != null){
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (!e.isEmpty()){
                if (client.getEmail() != null){
                    e.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null){
                    e.get().setPassword(client.getPassword());
                }
                if (client.getName() != null){
                    e.get().setName(client.getName());
                }
                if (client.getAge() != null){
                    e.get().setAge(client.getAge());
                }
                clientRepository.save(e.get());
                return e.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    /**
     * Este metodo borra un Client
     * @param idClient
     * @return
     */
    public boolean deleteClient(int idClient){
        Boolean aBoolean =getClient(idClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
