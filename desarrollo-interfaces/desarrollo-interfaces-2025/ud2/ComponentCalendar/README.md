# 📅 Component Calendar (Workout Edition)

Componente de calendario interactivo desarrollado en Java para la asignatura de **Desarrollo de Interfaces** (DAM). Su objetivo principal es la gestión visual de entrenamientos (*workouts*) asociados a fechas específicas.

## ✨ Características Principales
* **Indicadores visuales:** Los días que contienen entrenamientos asignados se destacan automáticamente en color **verde**.
* **Información emergente (Tooltips):** Al pasar el cursor por encima de un día con actividad, muestra un pequeño mensaje indicando el número exacto de entrenamientos programados para esa fecha.
* **Navegación flexible:** 
  * Dispone de dos `JComboBox` independientes para seleccionar directamente el **mes** y el **año**.
  * Soporta gestos de **swipe** para cambiar de mes de forma fluida.
* **Eventos personalizados:** Implementa un sistema de escucha (*listeners*) propio para la interacción del usuario.

---

## 📦 Instalación (Dependencia Maven)

Para utilizar este componente en tus proyectos, añade la siguiente dependencia en tu archivo `pom.xml`:

     <dependencies>
         <dependency>
            <groupId>Nerea</groupId>
            <artifactId>ComponentCalendar</artifactId>
            <version>1.0-SNAPSHOT</version>
         </dependency>
     </dependencies>


Añadir al constructor de tu pproyecto:

    calendarPanel1.addCalendarEventListener(new CalendarEventListener(){
      @Override
        public void onDayClicked(WorkoutCalendarEvent event) {
          jTextAreaWorkoutInfo.setText(event.toString());
        }
    });
