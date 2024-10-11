package com.metropolitan.services;

import com.metropolitan.dtos.KorisnikDTO;
import com.metropolitan.enums.Uloga;
import com.metropolitan.models.Korisnik;
import com.metropolitan.repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class KorisnikService implements UserDetailsService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByEmail(email);
        if (korisnik == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(korisnik.getUloga().toString()));
        return new User(korisnik.getEmail(),korisnik.getSifra(), authority);
    }

    public Korisnik registerKorisnik(KorisnikDTO korisnikDTO) {
        Korisnik korisnik=new Korisnik();
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());
        korisnik.setEmail(korisnikDTO.getEmail());
        korisnik.setTelefon(korisnikDTO.getTelefon());
        korisnik.setSifra(passwordEncoder.encode(korisnikDTO.getSifra()));
        korisnik.setAdresa(korisnikDTO.getAdresa());
        korisnik.setGrad(korisnikDTO.getGrad());
        korisnik.setUloga(Uloga.KLIJENT);
        return korisnikRepository.save(korisnik);
    }

    public Korisnik registerAdmin(KorisnikDTO korisnikDTO) {
        Korisnik admin = new Korisnik();
        admin.setIme(korisnikDTO.getIme());
        admin.setPrezime(korisnikDTO.getPrezime());
        admin.setEmail(korisnikDTO.getEmail());
        admin.setTelefon(korisnikDTO.getTelefon());
        admin.setSifra(passwordEncoder.encode(korisnikDTO.getSifra()));
        admin.setAdresa(korisnikDTO.getAdresa());
        admin.setGrad(korisnikDTO.getGrad());
        admin.setUloga(Uloga.ADMIN);
        return korisnikRepository.save(admin);

    }

    public Korisnik deleteKorisnik(int id) {
        Korisnik korisnik = korisnikRepository.findById(id);
        if(korisnik == null){
            return null;
        }
        korisnikRepository.delete(korisnik);
        return korisnik;
    }
}
