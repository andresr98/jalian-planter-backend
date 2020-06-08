package com.jalian.planter.service;

import com.jalian.planter.exception.BadRequestException;
import com.jalian.planter.exception.DataNotFoundException;
import com.jalian.planter.exception.InternalServerException;
import com.jalian.planter.model.Tip;
import com.jalian.planter.repository.TipRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipService {

    private TipRepository tipRepository;
    private final String NO_DATA = "No se encuentran datos en la base de datos";
    private final String NO_DATA_BY_ID = "No se encuentra el tip con el identificador ingresado";
    private final String CANNOT_CREATE = "No se pudo crear el tip";
    private final String BAD_REQUEST = "Parámetros incorrectos";


    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public List<Tip> getAllTips() {
        List<Tip> tips = tipRepository.findAll();

        if (tips.size() == 0) {
            throw new DataNotFoundException(NO_DATA);
        }

        return tips;
    }

    public Tip getTipById (int id) {
        Optional<Tip> optionalTip = tipRepository.findById(id);

        if (optionalTip.isPresent()) {
            return optionalTip.get();
        }

        throw new DataNotFoundException(NO_DATA_BY_ID);
    }

    public Tip createTip(Tip tip) {

        if (tip.getMessage() == "" || tip.getMessage() == null) {
            throw new BadRequestException(BAD_REQUEST);
        }

        Tip createdTip = tipRepository.save(tip);

        if (createdTip == null) {
            throw new InternalServerException(CANNOT_CREATE);
        }

        return createdTip;
    }

    public Tip updateTipById(int id, Tip tip) {
        final boolean existsTip = tipRepository.existsById(id);

        if (existsTip) {
            if (tip.getMessage() == "" || tip.getMessage() == null) {
                throw new BadRequestException(BAD_REQUEST);
            }
            tip.setId(id);
            Tip updatedTip = tipRepository.save(tip);
            return updatedTip;
        }

        throw new DataNotFoundException(NO_DATA_BY_ID);
    }
}
