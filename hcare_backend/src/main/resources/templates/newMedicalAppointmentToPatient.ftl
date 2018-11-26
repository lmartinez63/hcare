<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <style>
        table {
            font-family: calibri;
        }
        .titleMain {
            font-size: 28px;
            font-weight: bold;
            color: #597db7;
            text-transform: uppercase;
            margin-bottom: 20px;
            padding: 10px 20px 20px 20px;
        }
        .descrip {
            font-size: 14px;
            color: #252525;
            line-height: 32px;
                padding: 0 20px;
        }
        .separator {
            height: 40px;
        }
        .empresa {
            color: #597db7;
            font-weight: bold;
        }
        .pe {
            color: #a8c6e7;
            font-weight: bold;
        }
        table {
            background-color: #f1f5f8;
        }
		td {
			padding: 20px;
		}
		.imagenCita {
			position: absolute;
			bottom: 70px;
			right: 20px;
		}
    </style>
</head>
<body>
    <center>
    <table style="width: 750px; height: 600px;">
        <tr>
            <td>
				<table style="background:#fff;border: 1px solid #41afb0;width: 100%;height: 100%; position:relative;">
					<tr>
						<td>
							<div class="titleMain">Estimado(a) Sr(a) ${MedicalAppointment.fullName}</div>
							<div class="descrip">Su cita ha sido programada exitosamente: </div>
                            <div class="descrip">Fecha: ${MedicalAppointment.dateAppointmentDateFormatted}</div>
                            <div class="descrip">Hora: ${MedicalAppointment.dateAppointmentTimeFormatted}</div>
                            <div class="descrip">Doctor: ${Doctor.fullName}</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="padding-left: 40px">
              <div class="" >Por favor, presentarse 15 minutos antes de la hora indicada </div>
							<div class="" >Gracias por confiar en nosotros</div>
              <div class="" >&nbsp;</div>
              <div class="" >Atentamente:</div>
              <div class="" >Nova Clinic</div>
							<div class="imagenCita">
								<img src="${Properties["frontEndUrl"]}/static/img/cita.png" />
							</div>
						</td>
					</tr>
          <tr style="text-align: center;font-size: 12px;">
            <td>
              <div class="" >Por favor si tiene algun inconveniente con su cita programada llamar al 999 999 999</div>
              <div class="" >Gustosamente le atenderemos</div>
            </td>
          </tr>

				</table>
            </td>
        </tr>
    </table>
    </center>
</body>
</html>
