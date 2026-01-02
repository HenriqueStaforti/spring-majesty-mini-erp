package com.henrique.majesty.service.order;

import com.henrique.majesty.entity.order.OrderEntity;
import com.henrique.majesty.entity.order.OrderItemEntity;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class OrderPdfService {

    public void generateOrderPdf(OrderEntity order) {
        try {
            String html = buildHtml(order);
            generatePdf(html, new FileOutputStream("pedido_" + order.getId() + ".pdf"));
        } catch(Exception e) {
            System.out.println("Erro ao gerar PDF: " + e.getMessage());
        }
    }

    private String buildHtml(OrderEntity order) throws IOException {
        String template = new String(
                getClass()
                        .getResourceAsStream("/templates/template-pedido.html")
                        .readAllBytes()
        );

        // Monta as linhas dinâmicas
        StringBuilder itemsHtml = new StringBuilder();
        for (OrderItemEntity item : order.getItems()) {
            itemsHtml.append("<tr>")
                    .append("<td>").append(item.getProductName()).append("</td>")
                    .append("<td>").append(item.getProduct().getCode()).append("</td>")
                    .append("<td>").append(item.getQuantity()).append(item.getUnitOfMeasure()).append("</td>")
                    .append("<td>").append(String.format("R$ %.2f", item.getPrice())).append("</td>")
                    .append("<td>").append(String.format("R$ %.2f", item.getQuantity() * item.getPrice())).append("</td>")
                    .append("</tr>");
        }

        Instant submittedDate = order.getSubmittedDate();
        String submittedDateStr = submittedDate.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Substitui os placeholders
        return template
                .replace("{{orderNumber}}", order.getId().toString())
                .replace("{{clientName}}", order.getClient().getName())
                .replace("{{address}}", order.getClient().getAddress())
                .replace("{{district}}", order.getClient().getDistrict())
                .replace("{{city}}", order.getClient().getCity())
                .replace("{{submittedDate}}", submittedDateStr)
                .replace("{{paymentTerm}}", order.getPaymentTerm())
                .replace("{{total}}", String.format("%.2f", order.getAmount()))
                .replace("{{items}}", itemsHtml.toString());
    }

    private void generatePdf(String html, OutputStream out) throws IOException {

        PdfRendererBuilder builder = new PdfRendererBuilder();

        builder.useFastMode();
        builder.withHtmlContent(html, "/");
        builder.toStream(out);
        builder.run();

    }
}
