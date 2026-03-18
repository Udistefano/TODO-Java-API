const API = 'http://localhost:8080/api/tareas';
let tareas = [];
let filtro = 'todas';
let prioridadSeleccionada = 'MEDIA'; // default
const colorPrioridad = { ALTA: '#F28B82', MEDIA: '#F5C842', BAJA: '#6DDBA8' };

// ── Prioridad buttons ──
function setPrioridad(valor, btn) {
  prioridadSeleccionada = valor;
  document.querySelectorAll('.prioridad-btn').forEach(b => b.classList.remove('selected'));
  btn.classList.add('selected');
}

// ── Fetch all tasks ──
async function cargarTareas() {
  try {
    const res = await fetch(API);
    if (!res.ok) throw new Error();
    tareas = await res.json();
    renderizar();
  } catch {
    mostrarToast('No se pudo conectar con la API', 'error');
    document.getElementById('task-list').innerHTML =
      '<div class="empty"><div class="emoji">⚠️</div><p>No se pudo conectar con la API.<br>¿Está corriendo el servidor?</p></div>';
  }
}

// ── Create task ──
async function crearTarea() {
  const titulo = document.getElementById('input-titulo').value.trim();
  const descripcion = document.getElementById('input-desc').value.trim();
  const prioridad = prioridadSeleccionada;

  if (!titulo) { mostrarToast('El título no puede estar vacío', 'error'); return; }

  try {
    const res = await fetch(API, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ titulo, descripcion, prioridad })
    });
    if (!res.ok) throw new Error();
    document.getElementById('input-titulo').value = '';
    document.getElementById('input-desc').value = '';
    // resetear prioridad a MEDIA
    prioridadSeleccionada = 'MEDIA';
    document.querySelectorAll('.prioridad-btn').forEach(b => b.classList.remove('selected'));
    document.querySelector('.prioridad-btn.media').classList.add('selected');

    mostrarToast('Tarea agregada', 'success');
    await cargarTareas();
  } catch {
    mostrarToast('Error al crear la tarea', 'error');
  }
}

// ── Complete task ──
async function completarTarea(id) {
  try {
    const res = await fetch(`${API}/${id}/completar`, { method: 'PATCH' });
    if (!res.ok) throw new Error();
    mostrarToast('Tarea completada!', 'success');
    await cargarTareas();
  } catch {
    mostrarToast('Error al actualizar la tarea', 'error');
  }
}

// ── Delete task ──
async function eliminarTarea(id) {
  try {
    const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
    if (!res.ok) throw new Error();
    mostrarToast('Tarea eliminada', '');
    await cargarTareas();
  } catch {
    mostrarToast('Error al eliminar la tarea', 'error');
  }
}

// ── Render ──
function renderizar() {
  const lista = document.getElementById('task-list');
  const pendientes = tareas.filter(t => !t.completado);
  const completadas = tareas.filter(t => t.completado);

  document.getElementById('stat-total').textContent   = `${tareas.length} total`;
  document.getElementById('stat-pending').textContent = `${pendientes.length} pendientes`;
  document.getElementById('stat-done').textContent    = `${completadas.length} completadas`;

  let filtradas = tareas;
  if (filtro === 'pendientes')  filtradas = pendientes;
  if (filtro === 'completadas') filtradas = completadas;

  if (filtradas.length === 0) {
    const msgs = {
      todas:       { txt: '¡No hay tareas! Agregá una arriba.' },
      pendientes:  { txt: '¡Todo al día! No tenés pendientes.' },
      completadas: { txt: 'Todavía no completaste ninguna.' }
    };
    const m = msgs[filtro];
    lista.innerHTML = `<div class="empty"><p>${m.txt}</p></div>`;
    return;
  }

  lista.innerHTML = filtradas.map(t => `
    <div class="task-card ${t.completado ? 'done' : ''}" id="card-${t.id}">
      <div class="task-check" onclick="${t.completado ? '' : `completarTarea(${t.id})`}"
           title="${t.completado ? 'Ya completada' : 'Marcar como completada'}">
        <svg width="12" height="12" viewBox="0 0 12 12" fill="none">
          <path d="M2 6l3 3 5-5" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
      </div>
      <div class="task-body">
        <div class="task-title">${escapeHtml(t.titulo)}</div>
        ${t.descripcion ? `<div class="task-desc">${escapeHtml(t.descripcion)}</div>` : ''}
        <span style="font-size:11px; font-weight:700; color:${colorPrioridad[t.prioridad]}; margin-top:4px; display:inline-block;">
          ● ${t.prioridad}
        </span>
        <div class="task-date">${formatFecha(t.createdAt)}</div>
      </div>
      <button class="btn-delete" onclick="eliminarTarea(${t.id})" title="Eliminar">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round">
          <polyline points="3 6 5 6 21 6"></polyline>
          <path d="M19 6l-1 14H6L5 6"></path>
          <path d="M10 11v6M14 11v6"></path>
          <path d="M9 6V4h6v2"></path>
        </svg>
      </button>
    </div>
  `).join('');
}

// ── Filter ──
function setFiltro(nuevo, btn) {
  filtro = nuevo;
  document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
  btn.classList.add('active');
  renderizar();
}

// ── Enter key on input ──
document.getElementById('input-titulo').addEventListener('keydown', e => {
  if (e.key === 'Enter') crearTarea();
});

// ── Toast ──
function mostrarToast(msg, tipo) {
  const t = document.getElementById('toast');
  t.textContent = msg;
  t.className = `toast ${tipo} show`;
  setTimeout(() => t.classList.remove('show'), 2800);
}

// ── Helpers ──
function escapeHtml(str) {
  return (str || '').replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
}
function formatFecha(str) {
  if (!str) return '';
  try {
    const d = new Date(str);
    return d.toLocaleDateString('es-AR', { day:'2-digit', month:'short', year:'numeric', hour:'2-digit', minute:'2-digit' });
  } catch { return str; }
}

// ── Init ──
cargarTareas();