<html>
<head></head>

<body>
    <p>Estimado(a) Dr(a) ${doctor.firstName},</p>
    <#if mal??>
    <p>Le enviamos su lista de citas medicas para el dia de mañana</p>
    <#list mal as ma>
      <p>${ma.firstName} hora ${ma.dateAppointment?datetime?string('hh:mm a')}</p>
    </#list>
    <#else>
    <p>No tiene citas pendientes  para el dia de mañana</p>
    </#if>
    <p>Por favor cualquier consulta no dude en contactarnos</p>
</body>

</html>