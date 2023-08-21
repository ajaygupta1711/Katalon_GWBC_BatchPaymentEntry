/*package fmi
 import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
 import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
 import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
 import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
 import com.kms.katalon.core.annotation.Keyword
 import com.kms.katalon.core.checkpoint.Checkpoint
 import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
 import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
 import com.kms.katalon.core.model.FailureHandling
 import com.kms.katalon.core.testcase.TestCase
 import com.kms.katalon.core.testdata.TestData
 import com.kms.katalon.core.testobject.TestObject
 import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
 import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
 import internal.GlobalVariable
 import io.cucumber.tagexpressions.TagExpressionParser.True
 import com.testautomationguru.utility.PDFUtil
 import org.apache.pdfbox.pdmodel.PDDocument
 import org.apache.pdfbox.pdmodel.common.function.type4.BitwiseOperators.False
 import org.apache.pdfbox.text.PDFTextStripper
 import org.apache.pdfbox.text.TextPosition
 import org.stringtemplate.v4.compiler.CodeGenerator.region_return
 public class CompareDataFromPDF{
 def static ifExistsInPDF (String baseFile, String SearchData){
 PDDocument baseDoc = PDDocument.load(new File(baseFile))
 try {
 PDFTextStripper stripperBase = new PDFTextStripper()
 stripperBase.setStartPage(stripperBase.getStartPage())
 stripperBase.setEndPage(stripperBase.getEndPage())
 String  baseText =stripperBase.getText(baseDoc)
 boolean exists = false;
 List baseTextList = baseText.split('\n')
 int count = 0;
 for (String text in baseTextList){
 if(text.indexOf(SearchData) >= 0){
 exists = true;
 count ++;
 }
 }
 println '==================True: Base PDF contains SearchData: ' + SearchData + '====================='
 println 'SearchData Number: ' +  count
 if(!exists){
 println '==================False: Base PDF does not contain SearchData: '+ SearchData + '====================='
 }
 }finally{
 if(baseDoc != null){
 baseDoc.close()
 }
 }
 }
 def static compareShowAllDifference (String baseFile, String newFile){
 PDDocument baseDoc = PDDocument.load(new File(baseFile))
 PDDocument newDoc = PDDocument.load(new File(newFile))
 try {
 PDFTextStripper stripperBase = new PDFTextStripper()
 PDFTextStripper stripperNew = new PDFTextStripper()
 stripperBase.setStartPage(stripperBase.getStartPage())
 stripperNew.setStartPage(stripperNew.getStartPage())
 stripperBase.setEndPage(stripperBase.getEndPage())
 stripperNew.setEndPage(stripperNew.getEndPage())
 String  baseText =stripperBase.getText(baseDoc)
 String  newText =stripperNew.getText(newDoc)
 List exclusionList=[
 "\\d{12}",
 "\\d{7}",
 "\\d{2}/\\d{2}/\\d{4}",
 "\\d{2}:\\d{2} AM",
 "\\d{2}:\\d{2}AM",
 "\\d{2}:\\d{2}:\\d{2} AM",
 "\\d{2}:\\d{2} PM",
 "\\d{2}:\\d{2}PM",
 "\\d{2}:\\d{2}:\\d{2} PM",
 "\\\$\\S+",
 "Direct Bill",
 "Agency Bill",
 "\\d{2}-Pay",
 "\\d{1}-Pay"
 ]
 List failElement = []
 for(int i = 0; i< exclusionList.size();i++){
 baseText = baseText.replaceAll(exclusionList[i],"")
 newText = newText.replaceAll(exclusionList[i],"")
 }
 List baseTextList = baseText.split('\n')
 List newTextList = newText.split('\n')
 for (int i=0; i < newTextList.size() ;i++){
 if(!baseTextList.contains(newTextList.get(i))){
 List text = newTextList.get(i).split(":");
 for(int j = 0;j<text.size(); j++){
 boolean exists = false;
 for(int k = 0 ;k < baseTextList.size();k++){
 if(baseTextList.get(k).indexOf(text.get(j).trim()) >= 0 && (( k - i > 0 && k - i < 5) || (i - k > 0 && i - k < 5))){
 exists = true;
 break;
 }
 }
 if(!exists){
 println '==================False: Base PDF does not contain New PDF====================='
 println text.get(j).trim();
 }
 }
 }
 }
 println "--------------------------------------------------------------------------------------------------";
 for (int i=0; i < baseTextList.size() ;i++){
 if(!newTextList.contains(baseTextList.get(i))){
 List text = baseTextList.get(i).split(":");
 for(int j = 0;j<text.size(); j++){
 boolean exists = false;
 for(int k = 0 ;k < newTextList.size();k++){
 if(newTextList.get(k).indexOf(text.get(j).trim()) >= 0 && (( k - i > 0 && k - i < 5) || (i - k > 0 && i - k < 5))){
 exists = true;
 break;
 }
 }
 if(!exists){
 println '=====================False: New PDF does not contain Base PDF====================='
 println text.get(j).trim();
 }
 }
 }
 }
 }finally{
 if(baseDoc != null && newDoc !=null){
 baseDoc.close()
 newDoc.close()
 }
 }
 }
 def static compareShowFirstDifference (String baseFile, String newFile){
 PDDocument baseDoc = PDDocument.load(new File(baseFile))
 PDDocument newDoc = PDDocument.load(new File(newFile))
 try {
 PDFTextStripper stripperBase = new PDFTextStripper()
 PDFTextStripper stripperNew = new PDFTextStripper()
 stripperBase.setStartPage(stripperBase.getStartPage())
 stripperNew.setStartPage(stripperNew.getStartPage())
 stripperBase.setEndPage(stripperBase.getEndPage())
 stripperNew.setEndPage(stripperNew.getEndPage())
 String  baseText =stripperBase.getText(baseDoc)
 String  newText =stripperNew.getText(newDoc)
 List exclusionList=[
 "\\d{12}",
 "\\d{7}",
 "\\d{2}/\\d{2}/\\d{4}",
 "\\d{2}:\\d{2} AM",
 "\\d{2}:\\d{2}AM",
 "\\d{2}:\\d{2}:\\d{2} AM",
 "\\d{2}:\\d{2} PM",
 "\\d{2}:\\d{2}PM",
 "\\d{2}:\\d{2}:\\d{2} PM",
 "\\\$\\S+",
 "Direct Bill",
 "Agency Bill",
 "\\d{2}-Pay",
 "\\d{1}-Pay"
 ]
 List failElement = []
 for(int i = 0; i< exclusionList.size();i++){
 baseText = baseText.replaceAll(exclusionList[i],"")
 newText = newText.replaceAll(exclusionList[i],"")
 }
 List baseTextList = baseText.split('\n')
 List newTextList = newText.split('\n')
 List unMatch = new ArrayList();
 for (int i=0; i < newTextList.size() ;i++){
 if(!baseTextList.contains(newTextList.get(i))){
 List text = newTextList.get(i).split(":");
 boolean exists = false;
 for(int j = 0;j<text.size(); j++){
 for(int k = 0 ;k < baseTextList.size();k++){
 if(baseTextList.get(k).indexOf(text.get(j).trim()) >= 0 && (( k - i > 0 && k - i < 5) || (i - k > 0 && i - k < 5))){
 exists = true;
 break;
 }
 }
 }
 if(!exists){
 unMatch.add(i);
 }
 }
 }
 int count = 0;
 int sum = count;
 boolean flag = true;
 for(int indx = 0; indx < unMatch.size() - 1; indx++){
 if(flag == true){
 println '==================False: Base PDF does not contain New PDF====================='
 println newTextList.get(unMatch.get(indx));
 println 'Previous Data Hiden Rows:' + sum;
 sum = 0;
 }
 if(unMatch.get(indx + 1) - unMatch.get(indx) <= 3){
 flag = false;
 count++;
 sum = count;
 }else{
 flag = true;
 }
 }
 println "--------------------------------------------------------------------------------------------------";
 unMatch = new ArrayList();
 for (int i=0; i < baseTextList.size() ;i++){
 flag = true;
 if(!newTextList.contains(baseTextList.get(i))){
 List text = baseTextList.get(i).split(":");
 for(int j = 0;j<text.size(); j++){
 boolean exists = false;
 for(int k = 0 ;k < newTextList.size();k++){
 if(newTextList.get(k).indexOf(text.get(j).trim()) >= 0 && (( k - i > 0 && k - i < 5) || (i - k > 0 && i - k < 5))){
 exists = true;
 break;
 }
 }
 if(!exists){
 unMatch.add(i);
 }
 }
 }
 }
 for(int indx = 0; indx < unMatch.size() - 1; indx++){
 if(flag == true){
 println '=====================False: New PDF does not contain Base PDF====================='
 println baseTextList.get(unMatch.get(indx));
 println 'Previous Data Hiden Rows:' + sum;
 sum = 0;
 }
 if(unMatch.get(indx + 1) - unMatch.get(indx) <= 3){
 flag = false;
 count++;
 sum = count;
 }else{
 flag = true;
 }
 }
 }finally{
 if(baseDoc != null && newDoc !=null){
 baseDoc.close()
 newDoc.close()
 }
 }
 }
 }
 */