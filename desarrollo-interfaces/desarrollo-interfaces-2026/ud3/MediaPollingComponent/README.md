Nerea Montoya

# MediaPollingComponent
Componente java bean conectado a API. Notifica si hay archivos nuevos.

## Añadir componente como bean

1. Click derecho sobre la Palette --> Palette Manager
<img width="170" height="173" alt="Captura de pantalla 2025-12-06 211839" src="https://github.com/user-attachments/assets/36d4e7b4-a6d1-47af-a53f-c1361e120932" />

2.  Add from JAR
<img width="350" height="361" alt="Captura de pantalla 2025-12-06 211915" src="https://github.com/user-attachments/assets/3938b713-c03e-4599-872d-4dd015bdf11d" />

3.  Buscar el jar del componente --> Next
<img width="650" height="391" alt="Captura de pantalla 2025-12-06 212008" src="https://github.com/user-attachments/assets/32e7f22b-05ad-4da1-9181-6d2f93b25e89" />

4.  Seleccionar MediaPollingComponent --> Next
<img width="650" height="390" alt="Captura de pantalla 2025-12-06 212036" src="https://github.com/user-attachments/assets/9a25bfb4-d6a2-4cc5-b8bd-2e3830336f7f" />

5. Seleccionar Beans o dende se quiera añadir --> Finish --> Close
<img width="650" height="391" alt="Captura de pantalla 2025-12-06 212127" src="https://github.com/user-attachments/assets/e98accc2-ca55-4095-a2e0-cefc96bff2ad" />

<img width="416" height="44" alt="image" src="https://github.com/user-attachments/assets/d99b21ce-4dde-43ae-bd39-98513c141570" />

---

## Añadir JAR del componente

1. Click derecho sobre Dependencies --> Add Dependency
<img width="377" height="236" alt="image" src="https://github.com/user-attachments/assets/de88e2b3-a208-411d-af74-a38a9af75fc9" />


2.Añadir las siguientes propiedades --> Add

    <groupId>montoya</groupId>
    <artifactId>MediaPollingComponent</artifactId>
    <version>1.0-SNAPSHOT</version>
    
<img width="590" height="556" alt="Captura de pantalla 2025-12-06 213252" src="https://github.com/user-attachments/assets/65003d0e-11e1-4963-a9f5-13fd38bf5b34" />

3. Click derecho sobre MediaPolingComponent --> Manually install artifact
<img width="338" height="303" alt="Captura de pantalla 2025-12-06 213306" src="https://github.com/user-attachments/assets/e107110d-68ee-41bd-b3ae-5c6f50948e99" />

4. Buscar el jar del componente --> Install locally
<img width="536" height="276" alt="Captura de pantalla 2025-12-06 213315" src="https://github.com/user-attachments/assets/114c5f6a-eaa1-4999-803a-79dfa2b4c15b" />
<img width="600" height="378" alt="Captura de pantalla 2025-12-06 213322" src="https://github.com/user-attachments/assets/d44a0045-dbd8-4f10-9da1-16f5de7071e4" />
<img width="538" height="277" alt="Captura de pantalla 2025-12-06 213330" src="https://github.com/user-attachments/assets/b66cbcbb-fe4b-4618-a941-f1342929621d" />  

---

## Método que debe añadirse al código

    private void initializePolling(String token){
        
        mediaPollingComponent1.setToken(token);
        mediaPollingComponent.setApiUrl("https://difreenet9.azurewebsites.net");
        mediaPollingComponent1.setPollingInterval(5);
        
        mediaPollingComponent1.addMediaListener(new MediaListener() {
            @Override
            public void newMediaFound(MediaEvent me) {
                System.out.println("Nuevos medios encontrados: " + me.getMediaList().size() + " " + me.getMediaList());
            }
        });
        
        mediaPollingComponent1.setRunning(true);
    }
        
---

## Contenido del componente

* Propiedades

      apiUrl, running, pollingInterval, token y lastChecked
  
* Wrappers

      login, getNickName, getAllMedia, getMediaAddedSince, download y uploadFileMultipart
  
* Eventos

      addMediaListener y removeMediaListener
---

## Notas de uso

  1. Para un mejor funcionamiento, se recomienda poner un intervalo de entre 5 a 10 segundos.
  2. lastChecked se inicializa al activar running
  3. Componente ciego, solo es visible el icono
