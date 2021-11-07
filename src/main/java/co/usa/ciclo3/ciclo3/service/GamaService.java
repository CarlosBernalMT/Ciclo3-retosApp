package co.usa.ciclo3.ciclo3.service;


import co.usa.ciclo3.ciclo3.model.Gama;
import co.usa.ciclo3.ciclo3.repository.GamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamaService {

    @Autowired
    private GamaRepository gamaRepository;

    public List<Gama> getAll(){
        return gamaRepository.getAll();
    }

    public Optional<Gama> getGama(int id){
        return gamaRepository.getGama(id);
    }

    public Gama save(Gama g){
        if (g.getIdGama()==null){
            return gamaRepository.save(g);
        }else{
            Optional<Gama> gaux = gamaRepository.getGama(g.getIdGama());
            if (gaux.isEmpty()){
                return gamaRepository.save(g);
            }else{
                return g;
            }
        }
    }

    public Gama update(Gama gama){
        if (gama.getIdGama() != null){
            Optional<Gama> g = gamaRepository.getGama(gama.getIdGama());
            if (!g.isEmpty()){
                if (gama.getDescription() != null){
                    g.get().setDescription(gama.getDescription());
                }
                if (gama.getName() != null){
                    g.get().setName(gama.getName());
                }
                gamaRepository.save(g.get());
                return g.get();
            }else{
                return gama;
            }
        }else{
            return gama;
        }
    }

    public boolean deleteGama(int id){
        Boolean aBoolean = getGama(id).map(gama -> {
            gamaRepository.delete(gama);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
