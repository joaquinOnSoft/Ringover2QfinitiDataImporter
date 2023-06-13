package com.opentext.qfiniti.importer.ringover;

/**
 * Call type, Used to filter certain types of call.
 * <ul> 
 *    <li><strong>'ANSWERED'</strong> filters answered calls. </li>
 *    <li><strong>'MISSED'</strong> filters missed calls. </li>
 *    <li><strong>'OUT'</strong> filters outgoing calls. </li>
 *    <li><strong>'VOICEMAIL'</strong> filters calls ending on voicemail. </li>
 * </ul>
 * @see https://developer.ringover.com/#tag/calls/paths/~1calls/get
 * @see https://www.baeldung.com/java-enum-values
 */
public enum CallType {
	ANSWERED("ANSWERED"),
	MISSED("MISSED"),
	OUT("OUT"),
	VOICEMAIL("VOICEMAIL");
	
    public final String label;

    private CallType(String label) {
        this.label = label;
    }
}
