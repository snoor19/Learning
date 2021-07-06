package com.spero.learn;

import java.util.StringTokenizer;

public class HandleCreditTLOG {

	public static void main(String[] args) {
		String tlog = "2021-06-04 11:35:50,193:	|http-nio-8080-exec-1|1|7795349007|P|48|null|E|null|100.0|credit|CS1|2021-06-04 11:35:49.409|2021-06-04 11:35:49.409|-|0|R|0|1|null|-|null|-|-|||SITE1|[CRSUB=8,-,,created,]|[CONTENT_ID=key:value]|[CAMPAIGN_ID=null]|[TOTAL_CHG_AMNT=100.0]|[RECO:refId=fdf345345345ergfdgd,chrg_refid=sub_DEX6xcJ1HSW4CR]|[TSK = CS,7,8,2021-06-04 19:35:50.112,-]";
		HandleCreditTLOG creditTLOG = new HandleCreditTLOG();
		creditTLOG.handleCredit(tlog);
	
	}
	
	private void handleCredit(String pRecord) {
		String _subscriptionId = null;
		try {
			String _line = pRecord.trim();
			_line = replaceAll(_line, "||", "|null|");
			StringTokenizer st = new StringTokenizer(_line, "|");
			String _billTime = st.nextToken();
			_billTime = _billTime.substring(0, _billTime.indexOf(","));
			String _threadId = st.nextToken();
			String _siteId = st.nextToken();
			String _subId = st.nextToken();
			String _subscriberType = st.nextToken();
			_subscriptionId = st.nextToken();
			String _serviceKeyword = st.nextToken();
			String _chargeType = st.nextToken();
			String _parentKeyword = st.nextToken();
			if (_parentKeyword != null && _parentKeyword.trim().equalsIgnoreCase("null")) {
				_parentKeyword = null;
			}
			String _chargeAmount = st.nextToken();
			String _mode = st.nextToken();
			String _user = st.nextToken();
			String _requestDate = st.nextToken();
			String _invoiceDate = st.nextToken();
			if (!(_invoiceDate == null || _invoiceDate.equalsIgnoreCase("null")) && _invoiceDate.indexOf(".") != -1) {
				_invoiceDate = _invoiceDate.substring(0, _invoiceDate.indexOf("."));
			}
			String _expiryDate = st.nextToken();
			if (!(_expiryDate == null || _expiryDate.equalsIgnoreCase("null")) && _expiryDate.indexOf(".") != -1) {
				_expiryDate = _expiryDate.substring(0, _expiryDate.indexOf("."));
			}
			String _retryCount = st.nextToken();
			String _cycleStatus = st.nextToken();
			String _graceCount = st.nextToken();
			String _graceRetryCount = st.nextToken();

			/*
			 * SM-8328 For upgrade and auto-upgrade case setting service key as new service
			 * key
			 */
			String _newSrvKeyword = st.nextToken();

			if ((null != _newSrvKeyword) && !_newSrvKeyword.trim().isEmpty()
					&& !_newSrvKeyword.trim().equalsIgnoreCase("null") && !_newSrvKeyword.trim().equals("-")) {
				_serviceKeyword = _newSrvKeyword;
			}

			String _inferredSubStatus = st.nextToken();
			String chargeStatus = null;
			String _contentId = "";
			String recoData = "";
			String _transactionTime = null;
			String _refundAmount = null;
			String _nextBilleDate = null;
			String _validity = "0";
			String refId = null;
			String transaction_Id = null;
			String typeTR = null;
			while (st.hasMoreTokens()) {
				chargeStatus = st.nextToken();
				if (chargeStatus.indexOf("[") == -1 || chargeStatus.indexOf("=") == -1
						|| chargeStatus.indexOf("]") == -1) {
					continue;
				}
				String type = chargeStatus.substring(chargeStatus.indexOf("[") + 1, chargeStatus.indexOf("=")).trim();
				String status = null;

				if (type.equalsIgnoreCase("CONTENT_ID")) {
					status = chargeStatus.substring(chargeStatus.indexOf("=") + 1, chargeStatus.indexOf("]")).trim();
					if (status != null && !status.equalsIgnoreCase("null")) {
						if (_contentId != null && !_contentId.endsWith(",") && !_contentId.isEmpty()) {
							_contentId = _contentId + ",";
						}
						_contentId = _contentId + status;
					}
				} else if (type.contains("RECO")) {
					recoData = chargeStatus.substring(chargeStatus.indexOf(":") + 1, chargeStatus.indexOf("]")).trim();
					String[] recoParams = recoData.split(",");
					for (String string : recoParams) {
						String[] keyValue = string.split("=");
						if (keyValue != null && keyValue.length == 2) {
							if (keyValue[0].equalsIgnoreCase("refId"))
								refId = keyValue[1];
							else if (keyValue[0].equalsIgnoreCase("chrg_refid"))
								transaction_Id = keyValue[1];
						}
					}
					_contentId = _contentId + "," + recoData;

				} else if (type.equalsIgnoreCase("CRSUB")) {
					System.out.println("CRSUB-->"+ chargeStatus);
					status = chargeStatus.substring(chargeStatus.indexOf("=") + 1, chargeStatus.indexOf("]")).trim();
					String[] part = status.split(",");
					String scid = "";
					if (part.length >= 9) {
						scid = part[8];
					}
					int transactionType = Integer.valueOf(part[0]);
					if (transactionType == 8 || transactionType == 50)
						typeTR = "PENDING";
					else if (transactionType == 51)
						typeTR = "SUCCESS";
					else
						typeTR = "ERROR";
				}
			}
			System.out.println("Content_id:::"+ _contentId+", Type::"+typeTR);
			System.out.println("RefId ::"+refId+", transactionId::"+transaction_Id);
		} catch (Exception ex) {
			System.out.println("TLogParser:(lineParse)EXCEP for sbnId-->:" + _subscriptionId+ ex);
		}
	}
	
	private String replaceAll(String s, String oldText, String newText) {
        final int sLength = s.length();
        final int oldLength = oldText.length();
        final int newLength = newText.length();
        if (oldLength == 0) {
            throw new IllegalArgumentException("CANNOT REPLACE THE EMPTY STRING");
        }
        if (oldText.equals(newText)) {
            return s;
        }
        StringBuffer sb = null;
        if (newLength <= oldLength) {
            sb = new StringBuffer(sLength);
        } else {
            int c = 0; // count the number of replacements to optimize memory
            // usage
            int i = 0;
            while ((i = s.indexOf(oldText, i)) > -1) {
                i += oldLength;
                c++;
            }
            if (c == 0) {
                return s;
            }
            sb = new StringBuffer(sLength + c * (newLength - oldLength));
        }
        int i = 0, x = 0;
        while ((x = s.indexOf(oldText, i)) > -1) {
            sb.append(s.substring(i, x));
            sb.append(newText);
            i = x + oldLength;
        }
        sb.append(s.substring(i));
        return sb.toString();
    }
	private String addparam(String contentId, String adder) {
        System.out.println("in getparams contentId={} adder={}" + contentId +"::"+ adder);
        if (contentId != null && !contentId.endsWith(",") && !contentId.isEmpty()) {
            contentId = contentId + ",";
        }
        contentId = contentId + adder;
        return contentId;
    }
}
