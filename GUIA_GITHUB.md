# 🐙 Guía Rápida: Cómo Actualizar tu Repositorio en GitHub

Cada vez que hagas cambios en tu código (modificar archivos, crear nuevos, borrar, etc.) y quieras guardarlos en la nube, sigue estos 3 pasos simples en tu terminal:

## 1. Preparar los cambios (`git add`)
Este comando le dice a Git: "Toma en cuenta todos los archivos que modifiqué".

```bash
git add .
```

## 2. Guardar los cambios (`git commit`)
Este comando guarda una "foto" de tus cambios con un mensaje que explica qué hiciste.

```bash
git commit -m "Escribe aquí qué cambios hiciste"
```
*Ejemplo: `git commit -m "Corregí el error en el endpoint de clientes"`*

## 3. Subir a la nube (`git push`)
Este comando envía tus cambios guardados desde tu computadora hacia GitHub.

```bash
git push
```

---

## 💡 Resumen del Flujo de Trabajo

1. Haces modificaciones en tu código 👨‍💻
2. `git add .` (Preparar)
3. `git commit -m "mensaje"` (Guardar localmente)
4. `git push` (Subir a GitHub)

¡Y listo! Tus cambios estarán actualizados en internet.
