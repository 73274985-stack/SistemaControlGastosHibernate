package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import modelo.Gasto;
import servicio.GestorGastos;

public class VentanaPrincipal extends JFrame {

    private JTextField txtDescripcion;
    private JTextField txtCategoria;
    private JTextField txtMonto;
    private JTextField txtFecha;

    private JButton btnRegistrar;
    private JButton btnCalcular;
    private JButton btnEliminar;
    private JLabel lblTotal;

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private GestorGastos gestor;

    public VentanaPrincipal() {

        gestor = new GestorGastos();

        setTitle("Sistema de Control de Gastos Personales");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(5,2));

        lblTotal = new JLabel("Total Gastado: S/. 0.0");

        panelFormulario.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelFormulario.add(txtDescripcion);

        panelFormulario.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panelFormulario.add(txtCategoria);

        panelFormulario.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        panelFormulario.add(txtMonto);

        panelFormulario.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        btnRegistrar = new JButton("Registrar Gasto");
        panelFormulario.add(btnRegistrar);

        add(panelFormulario, BorderLayout.NORTH);

        String[] columnas = {
                "Descripción",
                "Categoría",
                "Monto",
                "Fecha"
        };

        modeloTabla = new DefaultTableModel(columnas,0);
        tabla = new JTable(modeloTabla);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        btnCalcular = new JButton("Calcular Monto Total");
        btnEliminar = new JButton("Quitar Gasto");

        panelBotones.add(lblTotal);
        panelBotones.add(btnCalcular);
        panelBotones.add(btnEliminar);

        add(panelBotones,BorderLayout.SOUTH);

        // Registrar gasto
        btnRegistrar.addActionListener(e -> {

            try {

                String descripcion = txtDescripcion.getText();
                String categoria = txtCategoria.getText();
                double monto = Double.parseDouble(txtMonto.getText());
                String fecha = txtFecha.getText();

                Gasto gasto = new Gasto(
                        descripcion,
                        categoria,
                        monto,
                        fecha
                );

                gestor.agregarGasto(gasto);

                cargarDatos();

                txtDescripcion.setText("");
                txtCategoria.setText("");
                txtMonto.setText("");
                txtFecha.setText("");

            } catch (NumberFormatException ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un monto válido"
                );

            }

        });

        // Calcular total
        btnCalcular.addActionListener(e -> {

            lblTotal.setText(
                    "Total Gastado: S/. " + gestor.calcularTotal()
            );

        });

        // Eliminar gasto
        btnEliminar.addActionListener(e -> {

            int filaSeleccionada = tabla.getSelectedRow();

            if(filaSeleccionada >= 0){

                gestor.eliminarGastoSeleccionado(filaSeleccionada);

                cargarDatos();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un gasto para eliminar"
                );

            }

        });

        cargarDatos();

        setVisible(true);

    }

    private void cargarDatos(){

        modeloTabla.setRowCount(0);

        for(Gasto gasto : gestor.obtenerGastos()){

            modeloTabla.addRow(new Object[]{
                    gasto.getDescripcion(),
                    gasto.getCategoria(),
                    gasto.getMonto(),
                    gasto.getFecha()
            });

        }

        lblTotal.setText(
                "Total Gastado: S/. " + gestor.calcularTotal()
        );

    }

}